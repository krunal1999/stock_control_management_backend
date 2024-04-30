package com.example.kbd6.backend.spaceallocationalgo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private long productid;
    private double length;
    private double breadth;
    private double height;
    private int quantity;


    public double getVolume() {
        return length * breadth * height;
    }
    public double getTotalVolume(){
        return getVolume() * quantity;
    }
}
