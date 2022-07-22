package com.example.restapimvc.payment.dto;

import com.example.restapimvc.pre.chat.ChatDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

public class PaymentDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class PaymentRequest {
        private String sourceId;
        private Integer amount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    @Builder
    public static class SquarePaymentRequest {
        @Getter
        @Setter
        @AllArgsConstructor
        @ToString
        @Builder
        public static class AmountMoney{
            private Integer amount;
            private String currency;
        }
        @JsonProperty("idempotency_key")
        private String idempotencyKey;
        @JsonProperty("amount_money")
        private AmountMoney amountMoney;
        @JsonProperty("source_id")
        private String accessToken;
    }


}
