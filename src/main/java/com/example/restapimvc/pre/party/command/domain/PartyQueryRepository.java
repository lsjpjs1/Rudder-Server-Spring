package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.pre.party.command.dto.PartyDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PartyQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QParty party = QParty.party;
    private final QPartyMember partyMember = QPartyMember.partyMember;

    public void findApplies(PartyDto.GetApplyListRequest getApplyListRequest){
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
}
