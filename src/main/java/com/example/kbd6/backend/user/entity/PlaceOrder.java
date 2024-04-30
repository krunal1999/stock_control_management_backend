package com.example.kbd6.backend.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "placedorder")
public class PlaceOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderid;
    private String totalamount;
    private String orderStatus;
    private Long username;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name="order_id" , referencedColumnName = "orderid")
    private List<OrderProduct> orderProduct;


}
