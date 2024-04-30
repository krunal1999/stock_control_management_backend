package com.example.kbd6.backend.controller;

import com.example.kbd6.backend.entity.PurchaseItemEntity;
import com.example.kbd6.backend.model.EmailSendData;
import com.example.kbd6.backend.model.PurchaseItemModel;
import com.example.kbd6.backend.service.Purchase.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchaseitem")
    public PurchaseItemModel savePurchaseItem(@RequestBody PurchaseItemModel purchaseItemModel){
        return purchaseService.savePurchaseItem(purchaseItemModel);
    }

    @GetMapping("/purchaseitem")
    public List<PurchaseItemModel> getAllPurchaseItem(){
        List<PurchaseItemModel> list = purchaseService.getAllPurchaseItemList();
        return list;
    }

    @GetMapping("/getbyid/{id}")
    public Optional<PurchaseItemEntity> getById(@PathVariable Long id){
        return purchaseService.getById(id);
    }

    @GetMapping("/purchaseitem/{purchaseid}")
    public PurchaseItemModel getByPurchaseId(@PathVariable String purchaseid){
        return purchaseService.getByPurchaseId(purchaseid);
    }

    @GetMapping("/sendmail")
    public void sendMail() throws FileNotFoundException {
        purchaseService.sendMail();
    }

    @PostMapping("/emaildetails")
    public EmailSendData getEmailDeails(@RequestBody EmailSendData emailSendData){

        return purchaseService.sendMailusingDetails(emailSendData);

    }

    @PostMapping("/updateorderstatus/{purchaseid}")
    public String updateOrderStatus(@PathVariable String purchaseid, @RequestBody String value){
        return purchaseService.updateOrderStatus(purchaseid, value);

    }

    @PostMapping("/reorderpurchase/{id}")
    public String getNewPurchaseId(@PathVariable String id){
        return purchaseService.getNewPurchaseId(id);
    }



}
