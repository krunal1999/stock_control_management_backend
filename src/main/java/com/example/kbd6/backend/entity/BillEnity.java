package com.example.kbd6.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill")
public class BillEnity {

    @Id
    @SequenceGenerator(name="bill_sequence", sequenceName = "bill_sequence",allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "bill_sequence")
    private Long id;
    private String bill_id;
    private String amount;
    private String productname;
    private String vendorname;

    private String billstatus;
    private String paidstatus;
    private String paymenttype;
    private String paymentdate;

}
