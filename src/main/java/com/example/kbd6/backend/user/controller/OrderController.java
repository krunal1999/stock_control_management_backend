package com.example.kbd6.backend.user.controller;

import com.example.kbd6.backend.user.entity.OrderProduct;
import com.example.kbd6.backend.user.entity.PlaceOrder;
import com.example.kbd6.backend.user.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/order")
@CrossOrigin("http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public PlaceOrder createOrder(@RequestBody PlaceOrder placeOrder){
        return orderService.saveOrder(placeOrder);
    }

    @GetMapping("/get/{id}")
    public PlaceOrder getOrderByid(@PathVariable Long id){

        return orderService.getOrderById(id);

    }

    @GetMapping("/getallorderproduct/{id}")
    public List<OrderProduct> getAllOrderProduct(@PathVariable String id){
        return orderService.getAllOrderProduct(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrderProduct(@PathVariable Long id){
        orderService.deleteById(id);
    }







}
