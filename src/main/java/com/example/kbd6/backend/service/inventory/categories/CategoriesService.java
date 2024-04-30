package com.example.kbd6.backend.service.inventory.categories;

import com.example.kbd6.backend.entity.CategoriesEntity;

import java.util.List;

public interface CategoriesService {
    CategoriesEntity addCategories(CategoriesEntity categories);

    List<CategoriesEntity> getAllCategories();

    CategoriesEntity findById(long id);

    void deleteProduct(long id);

    void updateStatus(long id);
}
