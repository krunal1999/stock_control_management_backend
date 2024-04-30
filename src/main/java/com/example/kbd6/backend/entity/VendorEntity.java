package com.example.kbd6.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vendor" , uniqueConstraints = @UniqueConstraint(name ="vendoruniquename" , columnNames = "vendoruniquename"))
public class VendorEntity {

    @Id
    @SequenceGenerator(name="vendor_sequence", sequenceName = "vendor_sequence",allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "vendor_sequence")
    private long vendor_id;
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
