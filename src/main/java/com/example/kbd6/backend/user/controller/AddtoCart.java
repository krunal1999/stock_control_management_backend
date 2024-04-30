package com.example.kbd6.backend.user.controller;

import com.example.kbd6.backend.user.entity.Cart;
import com.example.kbd6.backend.user.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/cart")
@CrossOrigin("http://localhost:3000")
public class AddtoCart {

    private final CartService cartService;


    public AddtoCart(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addtocart")
    public Cart addtoCart(@RequestBody Cart cart){
        return cartService.addToCart(cart);

    }

    @GetMapping("/getcartproduct/{id}")
    public List<Cart> getAllCartProduct(@PathVariable Long id){
        return cartService.getAllCartProduct(id);
    }

    @DeleteMapping("/removeproduct/{id}")
    public String deleteproduct(@PathVariable Long id){
        return cartService.deleteproduct(id);
    }

    @PostMapping("/updateaddquantity/{id}")
    public void updateAddProductQuantity(@PathVariable Long id){
         cartService.updateAddQuantity(id);

    }
    @PostMapping("/updateminusquantity/{id}")
    public void updateMinusProductQuantity(@PathVariable Long id){
        cartService.updateMinusQuantity(id);

    }
}
