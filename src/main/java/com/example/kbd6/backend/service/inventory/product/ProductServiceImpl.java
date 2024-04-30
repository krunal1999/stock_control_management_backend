package com.example.kbd6.backend.service.inventory.product;


import com.example.kbd6.backend.entity.ReceieveItemEnity;
import com.example.kbd6.backend.entity.UniqueProductEntity;
import com.example.kbd6.backend.entity.inventory.ProductEntity;
import com.example.kbd6.backend.entity.inventory.ProductImageEntity;
import com.example.kbd6.backend.repositary.ProductImageRepository;
import com.example.kbd6.backend.repositary.ProductRepositary;

import com.example.kbd6.backend.service.receieveitem.ReceieveItemService;
import com.example.kbd6.backend.spaceallocationalgo.model.Location;
import com.example.kbd6.backend.spaceallocationalgo.model.Product;
import com.example.kbd6.backend.spaceallocationalgo.service.Warehouse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepositary productRepositary;
    private final ProductImageRepository productImageRepository;
    private final ReceieveItemService receieveItemService;
    private final Warehouse warehouse;


    public ProductServiceImpl(ProductRepositary productRepositary, ProductImageRepository productImageRepository, ReceieveItemService receieveItemService, Warehouse warehouse) {
        this.productRepositary = productRepositary;
        this.productImageRepository = productImageRepository;
        this.receieveItemService = receieveItemService;
        this.warehouse = warehouse;

    }

    public void calculateVolume(ProductEntity product) {
        double length = product.getLength();
        double breadth = product.getBreadth();
        double height = product.getHeight();

        double volume = length * breadth * height;
        product.setVolume(volume);
    }

    public List<ProductEntity> getAllProducts() {
        getAllProductsDimension();
        List<ProductEntity> checkMinimumQuantity = productRepositary.findAll();

        for(ProductEntity entity: checkMinimumQuantity){
            if(entity.getMinimumQuantityAlert() >= entity.getRemainingquantity()){
                ProductEntity existingProduct = productRepositary.findById(entity.getId()).orElse(null);
                if(existingProduct != null){
                    existingProduct.setActivestatus("INACTIVE");
                    productRepositary.save(existingProduct);
                }
            }
        }

        return productRepositary.findAll();
    }

    public Location[] getAllProductsDimension() {
        List<ProductEntity> productEntities = productRepositary.findAll();
        List<Product> productdimension = new ArrayList<>();

        for(ProductEntity productEntity :productEntities){
            Product productdimensionSingle = new Product();
            productdimensionSingle.setProductid(productEntity.getId());
            productdimensionSingle.setHeight(productEntity.getHeight());
            productdimensionSingle.setBreadth(productEntity.getBreadth());
            productdimensionSingle.setLength(productEntity.getLength());
            productdimensionSingle.setQuantity(productEntity.getQuantity());
            productdimension.add(productdimensionSingle);
        }
        Product[] productDimensionArray = productdimension.toArray(new Product[0]);
        return warehouse.addProducts(productDimensionArray);

    }

    @Override
    public String updateSoldQuantity(Long id, int quantity) {
        ProductEntity existingEntity = productRepositary.findById(id).orElseThrow(()-> new RuntimeException("product not found"));
        int qty = existingEntity.getQuantity();

        int soldqty = existingEntity.getSoldquantity();
        int newSoldqty = soldqty+quantity;

        int remainingQuantity = qty - newSoldqty;

        existingEntity.setSoldquantity(newSoldqty);
        existingEntity.setRemainingquantity(remainingQuantity);

        productRepositary.save(existingEntity);
        return "ok";
    }

    @Override
    public List<ProductEntity> getAllProductsWithImages() {
        return productRepositary.findAll();
    }

    @Override
    public List<UniqueProductEntity> getAlluniqueListfromProduct() {
        List<ProductEntity> productEntityList = productRepositary.findAll();
        List<UniqueProductEntity> uniqueProductEntityList = new ArrayList<>();
        for(ProductEntity product : productEntityList  ){
            UniqueProductEntity singleUniqueProduct = new UniqueProductEntity();
            singleUniqueProduct.setUniquevendorname(product.getVendoruniquename());
            singleUniqueProduct.setCategory(product.getCategories());
            singleUniqueProduct.setProductid(product.getId());
            singleUniqueProduct.setUniqueproductname(product.getProductname());
            uniqueProductEntityList.add(singleUniqueProduct);
        }
        return uniqueProductEntityList;
    }

    @Override
    public String updateReorderQuantity(Long id, int quantity) {
        getAllProductsDimension();
        ProductEntity existingEntity = productRepositary.findById(id).orElseThrow(()-> new RuntimeException("product not found"));
        int qty = existingEntity.getQuantity();
        int newQty = qty + quantity;
        existingEntity.setQuantity(newQty);
        existingEntity.setRemainingquantity(newQty - existingEntity.getSoldquantity());
        productRepositary.save(existingEntity);
        return "ok";
    }


    public ProductEntity getProductById(Long id) {
        return productRepositary.findById(id).orElse(null);
    }

    public ProductEntity saveProduct(ProductEntity product, List<MultipartFile> images) throws IOException {

        calculateVolume(product);

        ProductEntity savedProduct = new ProductEntity();
        BeanUtils.copyProperties(product,savedProduct);

        List<ProductImageEntity> imageEntitylist = new ArrayList<>();

        for (MultipartFile image : images) {
            ProductImageEntity imageEntity = new ProductImageEntity();
            imageEntity.setFileName(image.getOriginalFilename());
            imageEntity.setFileType(image.getContentType());
            imageEntity.setImageData(image.getBytes());

            productImageRepository.save(imageEntity);
            imageEntitylist.add(imageEntity);
        }
        savedProduct.setImages(imageEntitylist);
        savedProduct.setActivestatus("ACTIVE");
        savedProduct.setProductstatus("NEW");
        savedProduct.setRemainingquantity(product.getQuantity() - product.getSoldquantity());
        productRepositary.save(savedProduct);
        long id = savedProduct.getId();
        savedProduct.setProductid("PID"+id);
        productRepositary.save(savedProduct);
        receieveItemService.changetheStatusToUsed(product.getReceieveItem());
        return savedProduct;

    }

    public void deleteProduct(Long id) {

        productRepositary.deleteById(id);
    }


    public ProductEntity updateProduct(ProductEntity product,List<MultipartFile> images) throws IOException {
        ProductEntity existingProduct = productRepositary.findById(product.getId()).orElse(null);
        if (images != null && !images.isEmpty()) {
            for (MultipartFile image : images) {
                System.out.println("Image Name: " + image.getName());
                System.out.println("Image Original Filename: " + image.getOriginalFilename());
                System.out.println("Image Content Type: " + image.getContentType());
                System.out.println("Image Size: " + image.getSize());

            }
        } else {
            System.out.println("No images provided");
        }
        if(existingProduct != null){
            List<ProductImageEntity> imageEntitylist = new ArrayList<>();
            boolean check = false;
            if (images != null && !images.isEmpty()) {
                for (MultipartFile image : images) {
                    if(image.getOriginalFilename().equals("empty.jpg") ){
                        System.out.println("Image Original Filename11: " + image.getOriginalFilename());
                        break;
                    }
                    else{
                        ProductImageEntity imageEntity = new ProductImageEntity();
                        imageEntity.setFileName(image.getOriginalFilename());
                        imageEntity.setFileType(image.getContentType());
                        imageEntity.setImageData(image.getBytes());
                        productImageRepository.save(imageEntity);
                        imageEntitylist.add(imageEntity);
                        check=true;
                    }
                }

            }
            if(check){
                existingProduct.setImages(imageEntitylist);
            }

            calculateVolume(product);
            existingProduct.setProductname(product.getProductname());
            existingProduct.setProductid(product.getProductid());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setCategories(product.getCategories());
            existingProduct.setAbout(product.getAbout());
            existingProduct.setTitle(product.getTitle());
            existingProduct.setBuyprice(product.getBuyprice());
            existingProduct.setSellingPrice(product.getSellingPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setMinimumQuantityAlert(product.getMinimumQuantityAlert());
            existingProduct.setAutoReorderEnabled(product.getAutoReorderEnabled());
            existingProduct.setLength(product.getLength());
            existingProduct.setBreadth(product.getBreadth());
            existingProduct.setHeight(product.getHeight());
            existingProduct.setVolume(product.getVolume());
            existingProduct.setActivestatus(product.getActivestatus());
            existingProduct.setProductstatus(product.getProductstatus());
            existingProduct.setRemainingquantity(product.getQuantity()-product.getSoldquantity());
            calculateVolume(product);
            productRepositary.save(existingProduct);
        }

        return existingProduct;

    }

    @Override
    public String getReorderId(Long id) {
        ProductEntity existingProduct = productRepositary.findById(id).orElse(null);

        if (existingProduct != null) {
            String tempRoid = existingProduct.getReceieveItem();
            String tempPurchaseId = "";
            List<ReceieveItemEnity> receieveItemEnityList = receieveItemService.getAllList();
            for (ReceieveItemEnity receieveItemEnity : receieveItemEnityList) {
                if (receieveItemEnity.getRoid().equals(tempRoid)) {
                    tempPurchaseId = receieveItemEnity.getPurchaseid();
                    return tempPurchaseId;
                }
            }
            return null;
        }
        return null;
    }


}
