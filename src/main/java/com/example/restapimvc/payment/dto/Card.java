
package com.example.restapimvc.payment.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "card_brand",
    "last_4",
    "exp_month",
    "exp_year",
    "fingerprint",
    "card_type",
    "prepaid_type",
    "bin"
})
@Generated("jsonschema2pojo")
public class Card {

    @JsonProperty("card_brand")
    private String cardBrand;
    @JsonProperty("last_4")
    private String last4;
    @JsonProperty("exp_month")
    private Integer expMonth;
    @JsonProperty("exp_year")
    private Integer expYear;
    @JsonProperty("fingerprint")
    private String fingerprint;
    @JsonProperty("card_type")
    private String cardType;
    @JsonProperty("prepaid_type")
    private String prepaidType;
    @JsonProperty("bin")
    private String bin;

    @JsonProperty("card_brand")
    public String getCardBrand() {
        return cardBrand;
    }

    @JsonProperty("card_brand")
    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    @JsonProperty("last_4")
    public String getLast4() {
        return last4;
    }

    @JsonProperty("last_4")
    public void setLast4(String last4) {
        this.last4 = last4;
    }

    @JsonProperty("exp_month")
    public Integer getExpMonth() {
        return expMonth;
    }

    @JsonProperty("exp_month")
    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    @JsonProperty("exp_year")
    public Integer getExpYear() {
        return expYear;
    }

    @JsonProperty("exp_year")
    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    @JsonProperty("fingerprint")
    public String getFingerprint() {
        return fingerprint;
    }

    @JsonProperty("fingerprint")
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    @JsonProperty("card_type")
    public String getCardType() {
        return cardType;
    }

    @JsonProperty("card_type")
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @JsonProperty("prepaid_type")
    public String getPrepaidType() {
        return prepaidType;
    }

    @JsonProperty("prepaid_type")
    public void setPrepaidType(String prepaidType) {
        this.prepaidType = prepaidType;
    }

    @JsonProperty("bin")
    public String getBin() {
        return bin;
    }

    @JsonProperty("bin")
    public void setBin(String bin) {
        this.bin = bin;
    }

}
