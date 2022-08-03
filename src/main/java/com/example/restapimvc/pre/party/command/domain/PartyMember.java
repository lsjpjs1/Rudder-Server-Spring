package com.example.restapimvc.pre.party.command.domain;

import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.PartyStatus;
import com.example.restapimvc.payment.PaymentHistory;
import com.example.restapimvc.util.converter.PartyStatusConverter;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "party_member")
@Table
@Builder
@AllArgsConstructor
public class PartyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyMemberId;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @ManyToOne(targetEntity = Party.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    private Party party;

    @Convert(converter = PartyStatusConverter.class)
    private PartyStatus partyStatus;

    private Integer numberApplicants;

    @ManyToOne(targetEntity = PaymentHistory.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_history_id")
    private PaymentHistory paymentHistory;

    public void approve() {
        if(party.getAlcoholId().equals(1l)){
            partyStatus = PartyStatus.HOST_APPROVE;
        }else{
            partyStatus = PartyStatus.ALCOHOL_PENDING;
        }

    }

}
