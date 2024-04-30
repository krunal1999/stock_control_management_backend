package com.example.kbd6.backend.model;


import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
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
    private int minimumQuantityAlert;
    private String autoReorderEnabled;
    private double length;
    private double breadth;
    private double height;
    private double volume;
    private String activestatus;
    private String productstatus;

}
