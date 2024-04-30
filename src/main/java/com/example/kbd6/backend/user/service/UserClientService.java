package com.example.kbd6.backend.user.service;

import com.example.kbd6.backend.entity.inventory.ProductEntity;
import com.example.kbd6.backend.repositary.ProductRepositary;
import com.example.kbd6.backend.user.userDTO.UserProductDto;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserClientService {

    private final ProductRepositary productRepositary;

    public UserClientService(ProductRepositary productRepositary) {
        this.productRepositary = productRepositary;
    }

    public List<UserProductDto> getallproduct() {

        List<ProductEntity> productEntities = productRepositary.findAll();
        List<UserProductDto> productDtos = new ArrayList<>();

        for(ProductEntity product : productEntities){
            UserProductDto userProductDto = new UserProductDto(product);
            productDtos.add(userProductDto);
        }

        return productDtos;
    }

    public UserProductDto getProductById(Long id) {
        ProductEntity entity = productRepositary.findById(id).orElse(null);
        if(entity != null){
            UserProductDto userProductDto = new UserProductDto(entity);
            return userProductDto;
        }
        return null;
    }
}
