package com.example.kbd6.backend.user.userDTO;


import com.example.kbd6.backend.entity.inventory.ProductEntity;
import com.example.kbd6.backend.entity.inventory.ProductImageEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProductDto {

    private Long id;
    private String productid;
    private String productname;
    private String brand;
    private String categories;
    private String about;
    private String title;
    private double sellingPrice;
    private int quantity;
    private int minimumQuantityAlert;
    private String autoReorderEnabled;
    private String activestatus;
    private String productstatus;
    private List<ProductImageEntity> images = new ArrayList<>();

    public UserProductDto(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.productid = productEntity.getProductid();
        this.productname = productEntity.getProductname();
        this.brand = productEntity.getBrand();
        this.categories = productEntity.getCategories();
        this.about = productEntity.getAbout();
        this.title = productEntity.getTitle();
        this.sellingPrice = productEntity.getSellingPrice();
        this.quantity = productEntity.getQuantity();
        this.minimumQuantityAlert = productEntity.getMinimumQuantityAlert();
        this.autoReorderEnabled = productEntity.getAutoReorderEnabled();
        this.activestatus = productEntity.getActivestatus();
        this.productstatus = productEntity.getProductstatus();
        this.images = productEntity.getImages();
    }
}
