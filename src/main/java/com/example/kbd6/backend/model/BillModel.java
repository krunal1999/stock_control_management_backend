package com.example.kbd6.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillModel {

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
