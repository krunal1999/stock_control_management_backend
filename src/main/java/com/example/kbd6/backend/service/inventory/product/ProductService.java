package com.example.kbd6.backend.service.inventory.product;

import com.example.kbd6.backend.entity.UniqueProductEntity;
import com.example.kbd6.backend.entity.inventory.ProductEntity;
import com.example.kbd6.backend.spaceallocationalgo.model.Location;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductEntity getProductById(Long id);

    void deleteProduct(Long id);

    ProductEntity saveProduct(ProductEntity updatedProduct,List<MultipartFile> images) throws IOException;

    List<ProductEntity> getAllProducts();

    List<ProductEntity> getAllProductsWithImages();

    List<UniqueProductEntity> getAlluniqueListfromProduct();

    String updateReorderQuantity(Long id, int quantity);

    public Location[] getAllProductsDimension();

    String updateSoldQuantity(Long id, int quantity);

    ProductEntity updateProduct(ProductEntity productEntity,List<MultipartFile> images) throws IOException;


    String getReorderId(Long id);
}
