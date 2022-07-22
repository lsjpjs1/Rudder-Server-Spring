
package com.example.restapimvc.payment.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "created_at",
    "risk_level"
})
@Generated("jsonschema2pojo")
public class RiskEvaluation {

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("risk_level")
    private String riskLevel;

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("risk_level")
    public String getRiskLevel() {
        return riskLevel;
    }

    @JsonProperty("risk_level")
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

}
