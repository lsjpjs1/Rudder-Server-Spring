
package com.example.restapimvc.payment.dto;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "square_product",
    "application_id"
})
@Generated("jsonschema2pojo")
public class ApplicationDetails {

    @JsonProperty("square_product")
    private String squareProduct;
    @JsonProperty("application_id")
    private String applicationId;

    @JsonProperty("square_product")
    public String getSquareProduct() {
        return squareProduct;
    }

    @JsonProperty("square_product")
    public void setSquareProduct(String squareProduct) {
        this.squareProduct = squareProduct;
    }

    @JsonProperty("application_id")
    public String getApplicationId() {
        return applicationId;
    }

    @JsonProperty("application_id")
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

}
