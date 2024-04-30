package com.example.kbd6.backend.controller;

import com.example.kbd6.backend.entity.UniqueProductEntity;
import com.example.kbd6.backend.service.Purchase.UniqueProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("uniqueproduct")
@CrossOrigin(origins = "http://localhost:3000")
public class UniqueProductController {

    private final UniqueProductService uniqueProductService;

    public UniqueProductController(UniqueProductService uniqueProductService) {
        this.uniqueProductService = uniqueProductService;
    }

    @GetMapping("/all")
    public List<UniqueProductEntity> getAllUniqueProductList(){
        return uniqueProductService.getAllUniqueProductList();
    }

    @PostMapping("/save")
    public UniqueProductEntity saveUniqueProduct(@RequestBody UniqueProductEntity uniqueProductEntity){

        return uniqueProductService.saveUniqueProduct(uniqueProductEntity);
    }
}
