package com.example.kbd6.backend.service.receieveitem;

import com.example.kbd6.backend.entity.PurchaseItemEntity;
import com.example.kbd6.backend.entity.ReceieveItemEnity;
import com.example.kbd6.backend.model.PurchaseItemModel;
import com.example.kbd6.backend.model.ReceieveditemModel;
import com.example.kbd6.backend.repositary.ReceieveItemRepositary;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceieveItemImpl implements ReceieveItemService{
    private final ReceieveItemRepositary receieveItemRepo;

    public ReceieveItemImpl(ReceieveItemRepositary receieveItemRepo) {
        this.receieveItemRepo = receieveItemRepo;
    }


    @Override
    public List<ReceieveItemEnity> getAllList() {
        return receieveItemRepo.findAll();
    }

    public void changetheStatusToUsed(String roid){
        ReceieveItemEnity enity = receieveItemRepo.findByRoid(roid);
        enity.setUsestatus(true);
        receieveItemRepo.save(enity);
    }
}
