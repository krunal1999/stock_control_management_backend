package com.example.kbd6.backend.spaceallocationalgo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {
    private int row;
    private int column;
    private int noOfshelves;
    private double totalVolume;
    private int breadthRequired;
    private long productId;
    private int quantity;
    private boolean occupied;
}
