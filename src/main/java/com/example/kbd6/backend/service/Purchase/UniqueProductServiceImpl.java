package com.example.kbd6.backend.service.Purchase;

import com.example.kbd6.backend.entity.UniqueProductEntity;
import com.example.kbd6.backend.repositary.UniqueProductRepo;
import com.example.kbd6.backend.service.inventory.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniqueProductServiceImpl implements UniqueProductService{
    private final UniqueProductRepo uniqueProductRepo;
    private final ProductService productService;


    public UniqueProductServiceImpl(UniqueProductRepo uniqueProductRepo, ProductService productService) {
        this.uniqueProductRepo = uniqueProductRepo;
        this.productService = productService;
    }

    @Override
    public List<UniqueProductEntity> getAllUniqueProductList() {
        return  productService.getAlluniqueListfromProduct();
    }


    @Override
    public UniqueProductEntity saveUniqueProduct(UniqueProductEntity uniqueProductEntity) {

        uniqueProductRepo.save(uniqueProductEntity);
        return uniqueProductEntity;
    }
}
