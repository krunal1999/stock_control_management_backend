package com.example.kbd6.backend.user.controller;

import com.example.kbd6.backend.user.entity.OrderSuceess;
import com.example.kbd6.backend.user.service.OrderSuccessService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/ordersuccess")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class OrderSuccessController {

    private final OrderSuccessService orderSuccessService;

    @PostMapping("/save")
    public OrderSuceess saveOrder(@RequestBody OrderSuceess orderSuceess){
        return orderSuccessService.saveOrder(orderSuceess);
    }

    @GetMapping("/getorder/{userid}")
    public List<OrderSuceess> getOrderById(@PathVariable Long userid){
        return orderSuccessService.getUserOrderById(userid);
    }

    @PostMapping("/refundproduct/{id}")
    public void userRefundRequest(@PathVariable Long id){
        orderSuccessService.userRefundService(id);
    }




}
