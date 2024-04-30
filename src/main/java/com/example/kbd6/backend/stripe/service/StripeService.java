package com.example.kbd6.backend.stripe.service;

import com.example.kbd6.backend.stripe.dto.CreatePayment;
import com.example.kbd6.backend.stripe.dto.CreatePaymentItem;
import com.example.kbd6.backend.stripe.dto.CreatePaymentResponse;
import com.example.kbd6.backend.user.entity.OrderSuceess;
import com.example.kbd6.backend.user.repo.OrderSuccessRepo;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.RefundCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
// bwlow code is referred from the strip , as it predefined and required code to work
// https://stripe.com/docs/checkout/quickstart
@Service
public class StripeService {
    private final OrderSuccessRepo orderSuccessRepo ;
    private String stripeApiKey = "sk_test_51NeDLBDAj2pgXHIpCjGT3R6kF0ZfO02G2xjcZZuWvVEf3i4qkT0060bxzWjYeXD7794dZT0AYMwz5HXPS2pQuGh100GZYK297e";
    public StripeService(OrderSuccessRepo orderSuccessRepo) {
        this.orderSuccessRepo = orderSuccessRepo;
    }

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeApiKey;
    }

    private int calculateOrderAmount(Object[] items) {
        int totalAmount = 0;
        for (Object item : items) {
            if (item instanceof CreatePaymentItem) {
                CreatePaymentItem paymentItem = (CreatePaymentItem) item;
                String itemIdString = paymentItem.getId();
                try {
                    int itemId = Integer.parseInt(itemIdString);
                    totalAmount += itemId;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return totalAmount*100;
    }

    public CreatePaymentResponse createPaymentIntent(CreatePayment createPayment) throws StripeException {
            int amount = calculateOrderAmount(createPayment.getItems());
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(new Long(amount))
                    .setCurrency("gbp")
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .build())
                    .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
            return paymentResponse ;
    }

    public String refundProduct(Long id) throws StripeException {
        OrderSuceess orderSuceess = orderSuccessRepo.findById(id).orElse(null);
        if(orderSuceess != null){
            RefundCreateParams params =
                    RefundCreateParams.builder()
                            .setPaymentIntent(orderSuceess.getClientIntent())
                            .setAmount((long) orderSuceess.getTotalprice()*100)
                            .build();
            Refund refund = Refund.create(params);
            CreatePaymentResponse paymentResponse = new CreatePaymentResponse(refund.getId());
            return paymentResponse.getClientSecret();
        }
        return null;
    }
}
