package com.example.kbd6.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorModel {
    private Long id;
    private String titles;
    private String firstname;
    private String lastname;
    private String vendoruniquename;
    private String brandname;
    private String email;
    private String location;
    private String phonenumber;
    private String country;
    private String activestatus;




}
