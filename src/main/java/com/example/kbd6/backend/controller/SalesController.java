package com.example.kbd6.backend.controller;

import com.example.kbd6.backend.user.entity.OrderSuceess;
import com.example.kbd6.backend.user.service.OrderService;
import com.example.kbd6.backend.user.service.OrderSuccessService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sales")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class SalesController {

    private final OrderSuccessService orderSuccessService;

    @GetMapping("/getallorder")
    public List<OrderSuceess> getAllOrder(){
        return orderSuccessService.getAllOrderSuccess();
    }

    @GetMapping("/getorder/{id}")
    public OrderSuceess getOrderById(@PathVariable Long id){
        return orderSuccessService.getOrderByID(id);
    }

    @PutMapping("/updatedeliverystatus/{id}")
    public OrderSuceess updateDeliveryStatus(@PathVariable Long id){
        return orderSuccessService.updateDeliveryStatus(id);
    }

    @PostMapping("/refunddecline/{id}")
    public void refundDeclineByAdmin(@PathVariable Long id){
        orderSuccessService.refundDeclineByAdmin(id);
    }

    @PostMapping("/refundproductsuccess/{id}/{intent}")
    public void userRefundRequest(@PathVariable Long id,@PathVariable String intent){
        orderSuccessService.userRefundSuccess(id,intent);
    }

}
