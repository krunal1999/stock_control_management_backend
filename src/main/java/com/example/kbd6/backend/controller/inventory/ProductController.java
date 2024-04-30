package com.example.kbd6.backend.controller.inventory;

import com.example.kbd6.backend.entity.UniqueProductEntity;
import com.example.kbd6.backend.entity.inventory.ProductEntity;
import com.example.kbd6.backend.service.inventory.product.ProductService;
import com.example.kbd6.backend.spaceallocationalgo.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/uniqueproduct")
    public List<UniqueProductEntity> getAlluniqueListfromProduct(){
        return productService.getAlluniqueListfromProduct();
    }


    @GetMapping("/images")
    public List<ProductEntity> getAllProductsWithImages() {
        return productService.getAllProductsWithImages();
    }

    @GetMapping("/getproductbrid/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable Long id) {
        ProductEntity product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestPart("product") ProductEntity productEntity,
                                                       @RequestParam(value = "image") List<MultipartFile> images) {
        try {
            ProductEntity createdProduct = productService.saveProduct(productEntity, images);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/updateproduct/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id, @RequestPart("product") ProductEntity productEntity,@RequestParam(value = "image") List<MultipartFile> images
                                                       ) {
        ProductEntity existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            productEntity.setId(id);
            try {
                ProductEntity createdProduct = productService.updateProduct(productEntity,images);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/{quantity}")
    public String updateReorderQuantity(@PathVariable Long id, @PathVariable int quantity){
        String existingEntity = productService.updateReorderQuantity(id , quantity);

    return "ok";
    }

    @PutMapping("/sold/{id}/{quantity}")
    public String updateSoldQuantity(@PathVariable Long id, @PathVariable int quantity){
        String existingEntity = productService.updateSoldQuantity(id , quantity);

        return "ok";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        ProductEntity product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/map")
    public Location[] getMap(){
        return productService.getAllProductsDimension();
    }

    @GetMapping("/reorder/{id}")
    public String getReorderId(@PathVariable Long id){
        return productService.getReorderId(id);
    }



}
