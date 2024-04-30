package com.example.kbd6.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReceieveditemModel {

    private long id;
    private String ro_id;
    private boolean usestatus;
    private String purchaseid;
    private String quantity;
    private String productname;
    private String orderstatus;
    private String receiveddate;
    private String productstatus;
    private String vendoruniquename;

}
