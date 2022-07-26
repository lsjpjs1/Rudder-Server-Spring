package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.domain.QSchool;
import com.example.restapimvc.domain.QUserPartyProfileImage;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.FilteringPeriod;
import com.example.restapimvc.enums.PartyPhase;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static com.querydsl.core.types.dsl.Expressions.nullExpression;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PartyQueryRepository {

    @Value("${cloud-front.url.post-image}")
    private String CLOUD_FRONT_POST_IMAGE_URL;

    private final JPAQueryFactory jpaQueryFactory;
    private final QParty party = QParty.party;
    private final QPartyMember partyMember = QPartyMember.partyMember;
    private final QSchool school = QSchool.school;
    private final QUserPartyProfileImage partyProfileImage = QUserPartyProfileImage.userPartyProfileImage;
    private final QAlcohol alcohol = QAlcohol.alcohol;

    public void findApplies(PartyDto.GetApplyListRequest getApplyListRequest) {
        jpaQueryFactory
                .select()
                .from(party)
                .leftJoin(partyMember).on(party.partyId.eq(partyMember.party.partyId))
                .where(
                        partyMember.userInfo.userInfoId.eq(getApplyListRequest.getUserInfoId()),
                        partyMember.partyStatus.eq(PartyStatus.HOST)
                )
                .fetch();
    }

    public List<PartyDto.PartyPreviewDto> findParties(PartyDto.GetPartiesRequest getPartiesRequest) {
        return jpaQueryFactory
                .select(
                        getPartyPreviewDtoConstructorExpression(getPartiesRequest.getUserInfoId(), getPartiesRequest.getSchoolId())
                )
                .from(party)
                .leftJoin(partyMember).on(partyMember.party.partyId.eq(party.partyId))
                .leftJoin(alcohol).on(alcohol.alcoholId.eq(party.alcoholId))
                .where(
                        lessThanPartyId(getPartiesRequest.getEndPartyId()),
                        filterByPeriod(getPartiesRequest.getFilteringPeriod()),
                        notCanceled()
                )
                .groupBy(party.partyId, party.partyTime)
                .orderBy(party.partyId.desc())
                .limit(20l)
                .fetch()
                ;
    }

    private ConstructorExpression<PartyDto.PartyPreviewDto> getPartyPreviewDtoConstructorExpression(Long userInfoId, Long schoolId) {
        return Projections.constructor(PartyDto.PartyPreviewDto.class,
                party.partyId,
                party.partyTitle.max(),
                new CaseBuilder().when(party.partyThumbnailName.isNull()).then("")
                        .otherwise(party.partyThumbnailName.prepend(CLOUD_FRONT_POST_IMAGE_URL))
                        .max(),
                party.partyTime.max(),
                party.totalNumberOfMember.max(),
                party.currentNumberOfMember.max(),
                new CaseBuilder().when(partyMember.partyStatus.eq(PartyStatus.PENDING)).then(partyMember.numberApplicants)
                        .otherwise(0)
                        .sum(),
                JPAExpressions.select(school.schoolName)
                        .from(school)
                        .where(school.schoolId.eq(schoolId)),
                new CaseBuilder().when(partyMember.userInfo.userInfoId.eq(userInfoId)).then(partyMember.partyStatus.stringValue())
                        .otherwise(Expressions.nullExpression())
                        .max()
                        .coalesce("NONE"),
                party.partyChatRoomId.max(),
                alcohol.alcoholName.max()
        );
    }

    public List<PartyDto.PartyOnlyDateDto> findPartiesMyHost(Long userInfoId) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(PartyDto.PartyOnlyDateDto.class,
                                party.partyId,
                                party.partyTime
                        )
                )
                .from(party)
                .where(
                        party.partyHostUserInfo.userInfoId.eq(userInfoId),
                        party.partyTime.gt(new Timestamp(System.currentTimeMillis())),
                        notCanceled()
                )
                .fetch()
                ;
    }

    public List<PartyDto.PartyPreviewDto> findApprovedParties(UserInfo userInfo) {
        return jpaQueryFactory
                .select(
                        getPartyPreviewDtoConstructorExpression(userInfo.getUserInfoId(), userInfo.getSchool().getSchoolId())
                )
                .from(party)
                .leftJoin(partyMember).on(partyMember.party.partyId.eq(party.partyId))
                .leftJoin(alcohol).on(alcohol.alcoholId.eq(party.alcoholId))
                .where(
                        partyMember.partyStatus.in(PartyStatus.HOST_APPROVE, PartyStatus.ALCOHOL_PENDING, PartyStatus.FINAL_APPROVE),
                        partyMember.userInfo.userInfoId.eq(userInfo.getUserInfoId()),
                        party.partyTime.gt(new Timestamp(System.currentTimeMillis())),
                        notCanceled()
                )
                .groupBy(party.partyId)
                .fetch()
                ;
    }

    public List<PartyDto.PartyPreviewDto> findPendingParties(UserInfo userInfo) {
        return jpaQueryFactory
                .select(
                        getPartyPreviewDtoConstructorExpression(userInfo.getUserInfoId(), userInfo.getSchool().getSchoolId())
                )
                .from(party)
                .leftJoin(partyMember).on(partyMember.party.partyId.eq(party.partyId))
                .leftJoin(alcohol).on(alcohol.alcoholId.eq(party.alcoholId))
                .where(
                        partyMember.partyStatus.eq(PartyStatus.PENDING),
                        partyMember.userInfo.userInfoId.eq(userInfo.getUserInfoId()),
                        party.partyTime.gt(new Timestamp(System.currentTimeMillis())),
                        notCanceled()
                )
                .groupBy(party.partyId)
                .fetch()
                ;
    }

    public PartyDto.PartyDetailDto findPartyDetail(Long partyId,Long userInfoId) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(PartyDto.PartyDetailDto.class,
                                party.partyId,
                                party.partyTitle.max(),
                                party.partyThumbnailName.max().prepend(CLOUD_FRONT_POST_IMAGE_URL),
                                party.partyTime.max(),
                                party.totalNumberOfMember.max(),
                                party.currentNumberOfMember.max(),
                                new CaseBuilder().when(partyMember.partyStatus.eq(PartyStatus.PENDING)).then(partyMember.numberApplicants)
                                        .otherwise(0)
                                        .sum(),
                                party.partyHostUserInfo.school.schoolName.max(),
                                party.location.max(),
                                alcohol.alcoholName.max(),
                                party.partyDescription.max(),
                                alcohol.alcoholImageName.max().prepend(CLOUD_FRONT_POST_IMAGE_URL),
                                alcohol.alcoholUnit.stringValue().max(),
                                alcohol.alcoholCount.max(),
                                alcohol.price.max().intValue(),
                                alcohol.currency.stringValue().max(),
                                new CaseBuilder().when(partyMember.userInfo.userInfoId.eq(userInfoId)).then(partyMember.partyStatus.stringValue())
                                        .otherwise(Expressions.nullExpression())
                                        .max()
                                        .coalesce("NONE")
                        )
                )
                .from(party)
                .leftJoin(partyMember).on(party.partyId.eq(partyMember.party.partyId))
                .leftJoin(alcohol).on(alcohol.alcoholId.eq(party.alcoholId))
                .where(party.partyId.eq(partyId))
                .groupBy(party.partyId)
                .fetchOne();
    }

    public List<PartyDto.PartyApplicantsDto> findApplicants(PartyDto.GetPartyApplicantsRequest getPartyApplicantsRequest) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(PartyDto.PartyApplicantsDto.class,
                                partyMember.userInfo.userInfoId.max(),
                                partyProfileImage.partyProfileImageName.max().prepend(CLOUD_FRONT_POST_IMAGE_URL),
                                partyMember.numberApplicants.max()
                        )
                )
                .from(partyMember)
                .leftJoin(partyProfileImage).on(partyMember.userInfo.userPartyProfile.userPartyProfileImageId.eq(partyProfileImage.partyProfileImageId))
                .where(
                        partyMember.partyStatus.eq(PartyStatus.PENDING),
                        equalPartyId(getPartyApplicantsRequest.getPartyId())
                )
                .groupBy(partyMember.partyMemberId)
                .fetch();
    }

    private BooleanExpression notCanceled() {
        return party.partyPhase.ne(PartyPhase.CANCEL);
    }

    private BooleanExpression equalPartyId(Long partyId) {
        if (partyId == null) {
            return null;
        }
        return partyMember.party.partyId.eq(partyId);
    }

    private BooleanExpression lessThanPartyId(Long partyId) {
        if (partyId == null) {
            return null;
        }
        return party.partyId.lt(partyId);
    }

    public BooleanExpression filterByPeriod(FilteringPeriod filteringPeriod) {
        if (filteringPeriod == null){
            return party.partyTime.gt(new Timestamp(System.currentTimeMillis()));
        }

        return party.partyTime.between(filteringPeriod.getStart(),filteringPeriod.getEnd());
    }

//    private BooleanExpression equalRecentPartyId(Long userInfoId) {
//        return partyMember.party.partyTime.eq(
//                JPAExpressions
//                        .select(partyMember.party.partyTime.max())
//                        .from(partyMember)
//                        .where(
//                                partyMember.partyStatus.eq(PartyStatus.PENDING),
//                                partyMember.userInfo.userInfoId.eq(userInfoId)
//                        )
//        );
//    }
}
