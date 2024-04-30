package com.example.kbd6.backend.stripe.dto;

import com.nimbusds.jose.shaded.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// bwlow code is referred from the strip , as it predefined and required code to work
// https://stripe.com/docs/checkout/quickstart
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePayment {
    @SerializedName("items")
    private CreatePaymentItem[] items;

    public CreatePaymentItem[] getItems() {
        return items;
    }

    public void setItems(CreatePaymentItem[] items) {
        this.items = items;
    }
}
