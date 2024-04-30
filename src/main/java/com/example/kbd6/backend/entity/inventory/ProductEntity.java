package com.example.kbd6.backend.entity.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productid;
    private String vendoruniquename;
    private String receieveItem;
    private String productname;
    private String brand;
    private String categories;
    @Lob
    private String about;
    private String title;
    private double buyprice;
    private double sellingPrice;
    private int quantity;

    private int remainingquantity;
    private int soldquantity;

    private int minimumQuantityAlert;
    private String autoReorderEnabled;
    private double length;
    private double breadth;
    private double height;
    private double volume;
    private String activestatus;
    private String productstatus;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<ProductImageEntity> images = new ArrayList<>();




}
