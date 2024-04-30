package com.example.kbd6.backend.user.service;

import com.example.kbd6.backend.user.entity.Cart;
import com.example.kbd6.backend.user.entity.OrderProduct;
import com.example.kbd6.backend.user.entity.PlaceOrder;
import com.example.kbd6.backend.user.repo.CartRepo;
import com.example.kbd6.backend.user.repo.OrderProductRepo;
import com.example.kbd6.backend.user.repo.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;
    private final CartRepo cartRepo;

    public OrderService(OrderRepo orderRepo, OrderProductRepo orderProductRepo, CartRepo cartRepo) {
        this.orderRepo = orderRepo;
        this.orderProductRepo = orderProductRepo;
        this.cartRepo = cartRepo;
    }

    public PlaceOrder saveOrder(PlaceOrder placeOrder) {
        List<PlaceOrder> placeOrderList = orderRepo.findAll();
        for(PlaceOrder placeOrder1:placeOrderList){
            if(placeOrder1.getUsername() != null){
                if (placeOrder1.getUsername().equals(placeOrder.getUsername()) ){
                    orderRepo.delete(placeOrder1);
                }
            }
            }
            return orderRepo.save(placeOrder);
    }


    public PlaceOrder getOrderById(Long id) {
        List<PlaceOrder> placeOrderList = orderRepo.findAll();
        for(PlaceOrder placeOrder:placeOrderList){
            if (placeOrder.getUsername().equals(id)){
                return placeOrder;
            }
        }
        return null;
    }


    public List<OrderProduct> getAllOrderProduct(String id) {
        List<OrderProduct> orderProductList = orderProductRepo.findAll();
        List<OrderProduct> orderProductList1 = new ArrayList<>();
        for(OrderProduct orderProduct: orderProductList){
            if(orderProduct.getUserid().equals(id)){
                orderProductList1.add(orderProduct);
            }
        }
        return orderProductList1;
    }

    
    public void deleteById(Long id) {
        List<PlaceOrder> placeOrderList = orderRepo.findAll();
        List<Cart> cartList = cartRepo.findAll();

        for(PlaceOrder placeOrder1:placeOrderList){
            if(placeOrder1.getUsername() != null){
                if (placeOrder1.getUsername().equals(id) ){
                    orderRepo.delete(placeOrder1);

                }
            }
        }
        for(Cart cart:cartList){
            if(cart.getUserid() != null){
                if (cart.getUserid().equals(id) ){
                    cartRepo.delete(cart);
                }
            }
        }


    }


}
