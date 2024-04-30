package com.example.kbd6.backend.repositary;

import com.example.kbd6.backend.entity.PurchaseItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepositary extends JpaRepository<PurchaseItemEntity , Long> {
    PurchaseItemEntity findByPurchaseid(String id);


}
