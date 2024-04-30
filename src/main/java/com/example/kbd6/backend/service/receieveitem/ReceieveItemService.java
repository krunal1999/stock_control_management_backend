package com.example.kbd6.backend.service.receieveitem;

import com.example.kbd6.backend.entity.ReceieveItemEnity;
import com.example.kbd6.backend.model.PurchaseItemModel;
import com.example.kbd6.backend.model.ReceieveditemModel;

import java.util.List;

public interface ReceieveItemService {

    List<ReceieveItemEnity> getAllList();
    void changetheStatusToUsed(String roid);
}
