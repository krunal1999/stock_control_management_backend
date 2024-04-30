package com.example.kbd6.backend.service.Purchase;

import com.example.kbd6.backend.entity.UniqueProductEntity;

import java.util.List;

public interface UniqueProductService {
    List<UniqueProductEntity> getAllUniqueProductList();

    UniqueProductEntity saveUniqueProduct(UniqueProductEntity uniqueProductEntity);
}
