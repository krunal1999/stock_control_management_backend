package com.example.kbd6.backend.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    private Long userId;
    private String username;
    private String firstname;
    private String lastname;
    private String addressline1;

    private String addressline2;
    private String city;
    private String state;
    private String country;
    private String postcode;
}
