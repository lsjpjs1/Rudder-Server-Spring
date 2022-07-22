
package com.example.restapimvc.payment.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "created_at",
    "updated_at",
    "amount_money",
    "app_fee_money",
    "status",
    "delay_duration",
    "source_type",
    "card_details",
    "location_id",
    "order_id",
    "reference_id",
    "risk_evaluation",
    "note",
    "customer_id",
    "total_money",
    "approved_money",
    "receipt_number",
    "receipt_url",
    "delay_action",
    "delayed_until",
    "application_details",
    "version_token"
})
@Generated("jsonschema2pojo")
public class Payment {

    @JsonProperty("id")
    private String id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("amount_money")
    private AmountMoney amountMoney;
    @JsonProperty("app_fee_money")
    private AppFeeMoney appFeeMoney;
    @JsonProperty("status")
    private String status;
    @JsonProperty("delay_duration")
    private String delayDuration;
    @JsonProperty("source_type")
    private String sourceType;
    @JsonProperty("card_details")
    private CardDetails cardDetails;
    @JsonProperty("location_id")
    private String locationId;
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("reference_id")
    private String referenceId;
    @JsonProperty("risk_evaluation")
    private RiskEvaluation riskEvaluation;
    @JsonProperty("note")
    private String note;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("total_money")
    private TotalMoney totalMoney;
    @JsonProperty("approved_money")
    private ApprovedMoney approvedMoney;
    @JsonProperty("receipt_number")
    private String receiptNumber;
    @JsonProperty("receipt_url")
    private String receiptUrl;
    @JsonProperty("delay_action")
    private String delayAction;
    @JsonProperty("delayed_until")
    private String delayedUntil;
    @JsonProperty("application_details")
    private ApplicationDetails applicationDetails;
    @JsonProperty("version_token")
    private String versionToken;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("amount_money")
    public AmountMoney getAmountMoney() {
        return amountMoney;
    }

    @JsonProperty("amount_money")
    public void setAmountMoney(AmountMoney amountMoney) {
        this.amountMoney = amountMoney;
    }

    @JsonProperty("app_fee_money")
    public AppFeeMoney getAppFeeMoney() {
        return appFeeMoney;
    }

    @JsonProperty("app_fee_money")
    public void setAppFeeMoney(AppFeeMoney appFeeMoney) {
        this.appFeeMoney = appFeeMoney;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("delay_duration")
    public String getDelayDuration() {
        return delayDuration;
    }

    @JsonProperty("delay_duration")
    public void setDelayDuration(String delayDuration) {
        this.delayDuration = delayDuration;
    }

    @JsonProperty("source_type")
    public String getSourceType() {
        return sourceType;
    }

    @JsonProperty("source_type")
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @JsonProperty("card_details")
    public CardDetails getCardDetails() {
        return cardDetails;
    }

    @JsonProperty("card_details")
    public void setCardDetails(CardDetails cardDetails) {
        this.cardDetails = cardDetails;
    }

    @JsonProperty("location_id")
    public String getLocationId() {
        return locationId;
    }

    @JsonProperty("location_id")
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @JsonProperty("order_id")
    public String getOrderId() {
        return orderId;
    }

    @JsonProperty("order_id")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("reference_id")
    public String getReferenceId() {
        return referenceId;
    }

    @JsonProperty("reference_id")
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    @JsonProperty("risk_evaluation")
    public RiskEvaluation getRiskEvaluation() {
        return riskEvaluation;
    }

    @JsonProperty("risk_evaluation")
    public void setRiskEvaluation(RiskEvaluation riskEvaluation) {
        this.riskEvaluation = riskEvaluation;
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    @JsonProperty("note")
    public void setNote(String note) {
        this.note = note;
    }

    @JsonProperty("customer_id")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customer_id")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("total_money")
    public TotalMoney getTotalMoney() {
        return totalMoney;
    }

    @JsonProperty("total_money")
    public void setTotalMoney(TotalMoney totalMoney) {
        this.totalMoney = totalMoney;
    }

    @JsonProperty("approved_money")
    public ApprovedMoney getApprovedMoney() {
        return approvedMoney;
    }

    @JsonProperty("approved_money")
    public void setApprovedMoney(ApprovedMoney approvedMoney) {
        this.approvedMoney = approvedMoney;
    }

    @JsonProperty("receipt_number")
    public String getReceiptNumber() {
        return receiptNumber;
    }

    @JsonProperty("receipt_number")
    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    @JsonProperty("receipt_url")
    public String getReceiptUrl() {
        return receiptUrl;
    }

    @JsonProperty("receipt_url")
    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    @JsonProperty("delay_action")
    public String getDelayAction() {
        return delayAction;
    }

    @JsonProperty("delay_action")
    public void setDelayAction(String delayAction) {
        this.delayAction = delayAction;
    }

    @JsonProperty("delayed_until")
    public String getDelayedUntil() {
        return delayedUntil;
    }

    @JsonProperty("delayed_until")
    public void setDelayedUntil(String delayedUntil) {
        this.delayedUntil = delayedUntil;
    }

    @JsonProperty("application_details")
    public ApplicationDetails getApplicationDetails() {
        return applicationDetails;
    }

    @JsonProperty("application_details")
    public void setApplicationDetails(ApplicationDetails applicationDetails) {
        this.applicationDetails = applicationDetails;
    }

    @JsonProperty("version_token")
    public String getVersionToken() {
        return versionToken;
    }

    @JsonProperty("version_token")
    public void setVersionToken(String versionToken) {
        this.versionToken = versionToken;
    }

}
