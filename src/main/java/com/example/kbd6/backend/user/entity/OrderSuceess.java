package com.example.kbd6.backend.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordersuceess")
public class OrderSuceess {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userid;
    private Long productid;
    private Long orderid;
    private String username;

    private String productname;
    private int quantity;
    private int sellingPrice;
    private int totalprice;

    private String clientIntent;
    private String clientIntentRefund;
    private String orderStatus;
    private String deliveryStatus;
    private String productTrackStatus;

    private String returnProduct;
    private String infoMessage;

}
