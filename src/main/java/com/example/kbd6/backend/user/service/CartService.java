package com.example.kbd6.backend.user.service;

import com.example.kbd6.backend.user.entity.Cart;
import com.example.kbd6.backend.user.repo.CartRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepo cartRepo;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public Cart addToCart(Cart cart) {
        List<Cart> cartList = getAllCartProduct(cart.getUserid());
        boolean productExists = false;

        for (Cart check : cartList) {
            if (check.getProductid().equals(cart.getProductid())) {
                Cart ogcart = cartRepo.findById(check.getId()).orElse(null);
                if (ogcart != null) {
                    int newQuantity = check.getQuantity() + cart.getQuantity();
                    ogcart.setQuantity(newQuantity);
                    return cartRepo.save(ogcart);
                }
            }
        }
        return cartRepo.save(cart);
    }
    public List<Cart> getAllCartProduct(Long id) {
        List<Cart> cartList = cartRepo.findAll();
        List<Cart> responseList = new ArrayList<>();

        cartList.forEach((check)->{
            if(check.getUserid().equals(id)){
                responseList.add(check);
            }
        });

        return responseList;
    }
    public String deleteproduct(Long id) {
        Cart cart = cartRepo.findById(id).orElse(null);
        if(cart != null){
            cartRepo.delete(cart);
            return "DELETED";
        }
        return "NOT DELETED";
    }

    public void updateAddQuantity(Long id) {
        Cart cart = cartRepo.findById(id).orElse(null);
        if(cart != null){
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepo.save(cart);
        }
    }

    public void updateMinusQuantity(Long id) {
        Cart cart = cartRepo.findById(id).orElse(null);
        if(cart != null){
            if(cart.getQuantity() > 1){
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepo.save(cart);
            } else if (cart.getQuantity() == 1) {
                deleteproduct(id);
            }

        }
    }
}
