package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.common.WithUserInfo;
import com.example.restapimvc.domain.QSchool;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
                        Projections.constructor(PartyDto.PartyPreviewDto.class,
                                party.partyId,
                                party.partyTitle.max(),
                                new NullExpression<>(String.class),
                                party.partyTime.max(),
                                new NullExpression<>(String.class),
                                party.totalNumberOfMember.max(),
                                party.currentNumberOfMember.max(),
                                new CaseBuilder().when(partyMember.partyStatus.eq(PartyStatus.PENDING)).then(1)
                                        .otherwise(0)
                                        .sum(),
                                school.schoolName.max()
                        )
                )
                .from(party)
                .leftJoin(school).on(school.schoolId.eq(getPartiesRequest.getSchoolId()))
                .leftJoin(partyMember).on(partyMember.party.partyId.eq(party.partyId))
                .groupBy(party.partyId)
                .limit(20l)
                .fetch()
                ;
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
                        party.partyHostUserInfoId.eq(userInfoId),
                        party.partyTime.gt(new Timestamp(System.currentTimeMillis()))
                )
                .fetch()
                ;
    }

    public PartyDto.GetPartyDetailResponse findPartyDetail(PartyDto.GetPartyDetailRequest getPartyDetailRequest) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(PartyDto.GetPartyDetailResponse.class,
                                party.partyId
                        )
                )
                .from(party)
                .where(party.partyId.eq(getPartyDetailRequest.getPartyId()))
                .fetchOne();
    }

    public List<PartyDto.PartyApplicantsDto> findApplicants(PartyDto.GetPartyApplicantsRequest getPartyApplicantsRequest) {
        return jpaQueryFactory
                .select(
                        Projections.constructor(PartyDto.PartyApplicantsDto.class,
                                partyMember.userInfo.userInfoId,
                                partyMember.userInfo.userPartyProfile.partyProfileImage.partyProfileImageName.prepend(CLOUD_FRONT_POST_IMAGE_URL)
                                )
                )
                .from(partyMember)
                .where(
                        partyMember.partyStatus.eq(PartyStatus.PENDING),
                        partyMember.userInfo.userInfoId.eq(getPartyApplicantsRequest.getUserInfoId()),
                        equalPartyId(getPartyApplicantsRequest.getPartyId())
                )
                .fetch();
    }

    private BooleanExpression equalPartyId(Long partyId) {
        if (partyId == null) {
            return null;
        }
        return partyMember.party.partyId.eq(partyId);
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
