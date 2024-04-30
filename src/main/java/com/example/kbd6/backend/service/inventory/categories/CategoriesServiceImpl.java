package com.example.kbd6.backend.service.inventory.categories;

import com.example.kbd6.backend.entity.CategoriesEntity;
import com.example.kbd6.backend.repositary.CategoriesRepositary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService{

    private final CategoriesRepositary categoriesRepositary;

    public CategoriesServiceImpl(CategoriesRepositary categoriesRepositary) {
        this.categoriesRepositary = categoriesRepositary;
    }

    @Override
    public CategoriesEntity addCategories(CategoriesEntity categories) {

        categories.setActivestatus("ACTIVE");
        categories.setType(categories.getType().toUpperCase());
        categoriesRepositary.save(categories);
        return categories;
    }

    @Override
    public List<CategoriesEntity> getAllCategories() {
        List<CategoriesEntity> list = new ArrayList<>();
        list = categoriesRepositary.findAll();
        return list;
    }

    @Override
    public CategoriesEntity findById(long id) {

        return categoriesRepositary.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(long id) {
        categoriesRepositary.deleteById(id);
    }

    @Override
    public void updateStatus(long id) {
        CategoriesEntity entity = categoriesRepositary.findById(id).orElse(null);
        if(entity.getActivestatus().equals("ACTIVE")){
            entity.setActivestatus("INACTIVE");
            categoriesRepositary.save(entity);
        }else{
            entity.setActivestatus("ACTIVE");
            categoriesRepositary.save(entity);


        }
    }
}
