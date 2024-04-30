package com.example.kbd6.backend.service.Purchase;

import com.example.kbd6.backend.entity.PurchaseItemEntity;
import com.example.kbd6.backend.model.EmailSendData;
import com.example.kbd6.backend.model.PurchaseItemModel;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    PurchaseItemModel savePurchaseItem(PurchaseItemModel purchaseItemModel);


    List<PurchaseItemModel> getAllPurchaseItemList();


    Optional<PurchaseItemEntity> getById(Long id);


    PurchaseItemModel getByPurchaseId(String purchaseid);


    void sendMail() throws FileNotFoundException;

    EmailSendData sendMailusingDetails(EmailSendData emailSendData);

    String updateOrderStatus(String purchaseid, String value);

    String getNewPurchaseId(String id);
}
