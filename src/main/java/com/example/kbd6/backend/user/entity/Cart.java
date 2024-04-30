package com.example.kbd6.backend.user.entity;

import com.example.kbd6.backend.entity.inventory.ProductImageEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productid;
    private Long userid;
    private String productname;
    private String brand;
    private String categories;
    private String about;
    private String title;
    private double sellingPrice;
    private int quantity;
    private int totalsellingprice;
    private int totalquantity;
    private int finalamount;
    private boolean paymentstatus;

    private String fileName;
    private String fileType;


    @Lob
    @Column(length = 5242880)
    private byte[] imageData;






}
