package com.example.restapimvc.controller;

import com.example.restapimvc.domain.QRecommendationCode;
import com.example.restapimvc.domain.QUserInfo;
import com.example.restapimvc.pre.party.command.domain.QPartyMember;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final JPAQueryFactory jpaQueryFactory;
    private final QRecommendationCode recommendationCode = QRecommendationCode.recommendationCode;
    private final QPartyMember partyMember = QPartyMember.partyMember;
    private final QUserInfo userInfo = QUserInfo.userInfo;

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Tmp{
        private String userEmail;
        private String userNickname;
        private String partyTitle;
        private String code;
    }
    public List<Tmp> findJobs() {
        return jpaQueryFactory
                .select(
                        Projections.constructor(Tmp.class,
                                partyMember.userInfo.userEmail,
                                partyMember.userInfo.userNickname,
                                partyMember.party.partyTitle,
                                recommendationCode.code
                        )
                )
                .from(recommendationCode)
                .leftJoin(partyMember).on(partyMember.partyMemberId.eq(recommendationCode.partyMemberId))
                .orderBy(partyMember.party.partyId.desc())
                .fetch();

    }

    @GetMapping("/reco")
    public String getReco() {

        AtomicReference<String> res = new AtomicReference<>("");
        findJobs().forEach(tmp -> {
            res.set(res + tmp.partyTitle+" / "+ tmp.userEmail+" / "+tmp.userNickname+" / "+tmp.code+"<br/>");
        });

        return res.get();
    }
}
