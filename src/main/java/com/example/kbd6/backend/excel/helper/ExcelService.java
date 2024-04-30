package com.example.kbd6.backend.excel.helper;

import com.example.kbd6.backend.entity.BillEnity;
import com.example.kbd6.backend.entity.CategoriesEntity;
import com.example.kbd6.backend.entity.PurchaseItemEntity;
import com.example.kbd6.backend.entity.VendorEntity;
import com.example.kbd6.backend.entity.inventory.ProductEntity;
import com.example.kbd6.backend.repositary.*;
import com.example.kbd6.backend.user.entity.Address;
import com.example.kbd6.backend.user.entity.OrderSuceess;
import com.example.kbd6.backend.user.repo.AddressRepo;
import com.example.kbd6.backend.user.repo.OrderSuccessRepo;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    private final ProductRepositary productRepositary;
    private final CategoriesRepositary categoriesRepositary;
    private final OrderSuccessRepo orderSuccessRepo;
    private final BillRepositarty billRepositarty;
    private final PurchaseRepositary purchaseRepositary;
    private final VendorRepositary vendorRepositary;
    private final AddressRepo addressRepo;

    public ExcelService(ProductRepositary productRepositary, CategoriesRepositary categoriesRepositary, OrderSuccessRepo orderSuccessRepo, BillRepositarty billRepositarty, PurchaseRepositary purchaseRepositary, VendorRepositary vendorRepositary, AddressRepo addressRepo) {
        this.productRepositary = productRepositary;
        this.categoriesRepositary = categoriesRepositary;
        this.orderSuccessRepo = orderSuccessRepo;
        this.billRepositarty = billRepositarty;
        this.purchaseRepositary = purchaseRepositary;
        this.vendorRepositary = vendorRepositary;
        this.addressRepo = addressRepo;
    }

    public ByteArrayInputStream getProductData() throws IOException {
        List<ProductEntity> productEntityList = productRepositary.findAll();
        return Helper.PoductdataToExcel(productEntityList);
    }

    public ByteArrayInputStream getCategoryData() throws IOException {
        List<CategoriesEntity> categoriesEntities = categoriesRepositary.findAll();
        return Helper.CategorydataToExcel(categoriesEntities);
    }

    public ByteArrayInputStream getOrdersuccessData() throws IOException {
        List<OrderSuceess> orderSuceessList = orderSuccessRepo.findAll();
        return Helper.OrderSuccessdataToExcel(orderSuceessList);
    }

    public ByteArrayInputStream getBillsData() throws IOException {
        List<BillEnity> billEnityList = billRepositarty.findAll();
        return Helper.BillsDatadataToExcel(billEnityList);
    }

    public ByteArrayInputStream getPurchaseitemdata() throws IOException  {
        List<PurchaseItemEntity> purchaseItemEntityList = purchaseRepositary.findAll();
        return Helper.PurchaseItemDataToExcel(purchaseItemEntityList);
    }

    public ByteArrayInputStream getVendordatadata() throws IOException  {
        List<VendorEntity> vendorEntityList = vendorRepositary.findAll();
        return Helper.VendorDataToExcel(vendorEntityList);
    }

    public ByteArrayInputStream getUserdatadata() throws IOException{
        List<Address> addressList = addressRepo.findAll();
        return Helper.UserDataToExcel(addressList);

    }
}
