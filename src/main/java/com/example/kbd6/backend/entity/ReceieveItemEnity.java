package com.example.kbd6.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "receieveditem")
@Builder
public class ReceieveItemEnity {

    @Id
    @SequenceGenerator(name="receieveditem_sequence", sequenceName = "receieveditem_sequence",allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "receieveditem_sequence")
    private long id;
    private String roid;
    private boolean usestatus;
    private String purchaseid;
    private String quantity;
    private String productname;
    private String orderstatus;
    private String receiveddate;
    private String productstatus;
    private String vendoruniquename;



}
