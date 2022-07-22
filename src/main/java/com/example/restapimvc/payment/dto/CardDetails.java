
package com.example.restapimvc.payment.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "card",
    "entry_method",
    "cvv_status",
    "avs_status",
    "auth_result_code",
    "statement_description",
    "card_payment_timeline"
})
@Generated("jsonschema2pojo")
public class CardDetails {

    @JsonProperty("status")
    private String status;
    @JsonProperty("card")
    private Card card;
    @JsonProperty("entry_method")
    private String entryMethod;
    @JsonProperty("cvv_status")
    private String cvvStatus;
    @JsonProperty("avs_status")
    private String avsStatus;
    @JsonProperty("auth_result_code")
    private String authResultCode;
    @JsonProperty("statement_description")
    private String statementDescription;
    @JsonProperty("card_payment_timeline")
    private CardPaymentTimeline cardPaymentTimeline;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("card")
    public Card getCard() {
        return card;
    }

    @JsonProperty("card")
    public void setCard(Card card) {
        this.card = card;
    }

    @JsonProperty("entry_method")
    public String getEntryMethod() {
        return entryMethod;
    }

    @JsonProperty("entry_method")
    public void setEntryMethod(String entryMethod) {
        this.entryMethod = entryMethod;
    }

    @JsonProperty("cvv_status")
    public String getCvvStatus() {
        return cvvStatus;
    }

    @JsonProperty("cvv_status")
    public void setCvvStatus(String cvvStatus) {
        this.cvvStatus = cvvStatus;
    }

    @JsonProperty("avs_status")
    public String getAvsStatus() {
        return avsStatus;
    }

    @JsonProperty("avs_status")
    public void setAvsStatus(String avsStatus) {
        this.avsStatus = avsStatus;
    }

    @JsonProperty("auth_result_code")
    public String getAuthResultCode() {
        return authResultCode;
    }

    @JsonProperty("auth_result_code")
    public void setAuthResultCode(String authResultCode) {
        this.authResultCode = authResultCode;
    }

    @JsonProperty("statement_description")
    public String getStatementDescription() {
        return statementDescription;
    }

    @JsonProperty("statement_description")
    public void setStatementDescription(String statementDescription) {
        this.statementDescription = statementDescription;
    }

    @JsonProperty("card_payment_timeline")
    public CardPaymentTimeline getCardPaymentTimeline() {
        return cardPaymentTimeline;
    }

    @JsonProperty("card_payment_timeline")
    public void setCardPaymentTimeline(CardPaymentTimeline cardPaymentTimeline) {
        this.cardPaymentTimeline = cardPaymentTimeline;
    }

}
