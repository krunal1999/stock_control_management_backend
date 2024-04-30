package com.example.kbd6.backend.controller;

import com.example.kbd6.backend.stripe.service.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
public class AdmintripeController {

    private final StripeService stripeService;


    public AdmintripeController(StripeService stripeService) {
        this.stripeService = stripeService;

    }

    @PostMapping("/sales/refund/{id}")
    public String refundProduct(@PathVariable Long id) throws StripeException {
         return stripeService.refundProduct(id);
    }


}
