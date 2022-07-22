
package com.example.restapimvc.payment.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "authorized_at",
    "captured_at"
})
@Generated("jsonschema2pojo")
public class CardPaymentTimeline {

    @JsonProperty("authorized_at")
    private String authorizedAt;
    @JsonProperty("captured_at")
    private String capturedAt;

    @JsonProperty("authorized_at")
    public String getAuthorizedAt() {
        return authorizedAt;
    }

    @JsonProperty("authorized_at")
    public void setAuthorizedAt(String authorizedAt) {
        this.authorizedAt = authorizedAt;
    }

    @JsonProperty("captured_at")
    public String getCapturedAt() {
        return capturedAt;
    }

    @JsonProperty("captured_at")
    public void setCapturedAt(String capturedAt) {
        this.capturedAt = capturedAt;
    }

}
