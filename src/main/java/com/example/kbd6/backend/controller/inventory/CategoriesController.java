package com.example.kbd6.backend.controller.inventory;

import com.example.kbd6.backend.entity.CategoriesEntity;
import com.example.kbd6.backend.service.inventory.categories.CategoriesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping("/add")
    public CategoriesEntity addCategories(@RequestBody CategoriesEntity categories){

        return categoriesService.addCategories(categories);

    }

    @GetMapping("/get")
    public List<CategoriesEntity> getAllCategories(){
        return categoriesService.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public String deleteCategories(@PathVariable long id){
        CategoriesEntity categories = categoriesService.findById(id);
        if(categories != null){
            categoriesService.deleteProduct(id);
            return "DELETED";
        }else{
            return "FAILED TO DELETE";
        }
    }

    @PutMapping("/{id}")
    public String updateCategoryStatus(@PathVariable long id){
        CategoriesEntity categories = categoriesService.findById(id);
        if(categories != null){
            categoriesService.updateStatus(id);
            return "UPDATED";
        }else{
            return "FAILED TO UPDATE";
        }
    }

}
