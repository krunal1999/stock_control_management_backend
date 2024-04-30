package com.example.kbd6.backend.user.service;

import com.example.kbd6.backend.user.entity.OrderSuceess;
import com.example.kbd6.backend.user.repo.OrderRepo;
import com.example.kbd6.backend.user.repo.OrderSuccessRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderSuccessService {

    private final OrderSuccessRepo orderSuccessRepo ;

    public OrderSuceess saveOrder(OrderSuceess orderSuceess) {
        orderSuceess.setDeliveryStatus("NOT DELIVERED");
        orderSuceess.setReturnProduct("POSITIVE");
        orderSuceess.setTotalprice(orderSuceess.getQuantity() * orderSuceess.getSellingPrice());
        return orderSuccessRepo.save(orderSuceess);
    }

    public List<OrderSuceess> getAllOrderSuccess(){
        return orderSuccessRepo.findAll();
    }

    public OrderSuceess getOrderByID(Long id) {
        return orderSuccessRepo.findById(id).orElse(null);
    }

    public OrderSuceess updateDeliveryStatus(Long id) {


        OrderSuceess orderSuceess = orderSuccessRepo.findById(id).orElse(null);
        if(orderSuceess != null){
            orderSuceess.setDeliveryStatus("DELIVERED");
            return orderSuccessRepo.save(orderSuceess);
        }
       return null;
    }

    public List<OrderSuceess> getUserOrderById(Long userid) {
        List<OrderSuceess> orderSuceessList = orderSuccessRepo.findAll();
        List<OrderSuceess> orderSuceessList1 = new ArrayList<>();
        for (OrderSuceess orderSuceess:orderSuceessList){
            if(orderSuceess.getUserid().equals(userid)){
                orderSuceessList1.add(orderSuceess);
            }
        }
        return orderSuceessList1;

    }


    public void userRefundService(Long id) {
        OrderSuceess orderSuceess = orderSuccessRepo.findById(id).orElse(null);
        if(orderSuceess != null){
            orderSuceess.setReturnProduct("USERREFUND");
            orderSuceess.setInfoMessage("Refund initiated");
            orderSuccessRepo.save(orderSuceess);
        }
    }

    public void refundDeclineByAdmin(Long id) {
        OrderSuceess orderSuceess = orderSuccessRepo.findById(id).orElse(null);
        if(orderSuceess != null){
            orderSuceess.setReturnProduct("DECLINED");
            orderSuceess.setInfoMessage("Refund Declined");
            orderSuccessRepo.save(orderSuceess);
        }
    }

    public void userRefundSuccess(Long id,String intent) {
        OrderSuceess orderSuceess = orderSuccessRepo.findById(id).orElse(null);
        if(orderSuceess != null){
            orderSuceess.setReturnProduct("ACCEPTED");
            orderSuceess.setInfoMessage("Refund PROCESSED");
            orderSuceess.setClientIntentRefund(intent);
            orderSuccessRepo.save(orderSuceess);
        }
    }
}
