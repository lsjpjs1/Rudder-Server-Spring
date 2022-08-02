package com.example.restapimvc.payment;

import com.example.restapimvc.domain.School;
import com.example.restapimvc.domain.UserInfo;
import com.example.restapimvc.enums.PaymentStatus;
import com.example.restapimvc.serializer.SchoolSerializer;
import com.example.restapimvc.util.converter.NotificationTypeConverter;
import com.example.restapimvc.util.converter.PaymentStatusConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Builder
@AllArgsConstructor
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentHistoryId;

    private String squarePaymentId;

    private Timestamp paymentTime;

    @ManyToOne(targetEntity = UserInfo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    private String idempotencyKey;

    @Convert(converter = PaymentStatusConverter.class)
    private PaymentStatus paymentStatus;

    private Integer paymentAmount;

}
