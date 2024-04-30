package com.example.kbd6.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItemModel {

    private long id;
    private String purchaseid;
    private String vendoruniquename;
    private String currentdate;
    private String expectdate;
    private String productname;
    private String categories;
    private String buyprice;
    private String quantity;
    private String amount;
    private String subtotal;
    private String tax;
    private String taxamount;
    private String total;
    private String orderstatus ;
    private String billstatus ;
    private String productStatus;
    private long reorderproductid;
}
