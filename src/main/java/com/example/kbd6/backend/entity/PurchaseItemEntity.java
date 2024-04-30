package com.example.kbd6.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="purchaseitem" , uniqueConstraints = @UniqueConstraint(name ="purchaseid_unique" , columnNames = "purchaseid"))
public class PurchaseItemEntity {

    @Id
    @SequenceGenerator(name="purchaseitem_sequence", sequenceName = "purchaseitem_sequence",allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE , generator = "purchaseitem_sequence")
    private long po_id;
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


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="receievedItem" , referencedColumnName = "id")
    private ReceieveItemEnity receieveItemEnity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bills" , referencedColumnName = "id")
    private BillEnity billEnity;





}
