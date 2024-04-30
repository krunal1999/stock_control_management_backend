package com.example.kbd6.backend.stripe.controller;

import com.example.kbd6.backend.stripe.dto.CreatePayment;
import com.example.kbd6.backend.stripe.dto.CreatePaymentResponse;
import com.example.kbd6.backend.stripe.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/stripe")
@RestController
public class StripeController {

    private final StripeService stripeService;

    public StripeController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/createpaymentintent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {
        return stripeService.createPaymentIntent(createPayment);
    }


}
