package com.example.kbd6.backend.service.Purchase;

import com.example.kbd6.backend.entity.BillEnity;
import com.example.kbd6.backend.entity.PurchaseItemEntity;
import com.example.kbd6.backend.entity.ReceieveItemEnity;
import com.example.kbd6.backend.entity.UniqueProductEntity;
import com.example.kbd6.backend.generatePdf.service.PdfService;
import com.example.kbd6.backend.gmail.Gmailsender;
import com.example.kbd6.backend.model.EmailSendData;
import com.example.kbd6.backend.model.PurchaseItemModel;
import com.example.kbd6.backend.model.ReorderModel;
import com.example.kbd6.backend.repositary.PurchaseRepositary;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchaseRepositary purchaseRepositary;

    private final PdfService pdfService;

    private final UniqueProductService uniqueProductService;



    public PurchaseServiceImpl(PurchaseRepositary purchaseRepositary, PdfService pdfService, UniqueProductService uniqueProductService) {
        this.purchaseRepositary = purchaseRepositary;
        this.pdfService = pdfService;
        this.uniqueProductService = uniqueProductService;
    }

    public ReorderModel checkUniqueProduct(PurchaseItemModel purchaseItemModel){
        List<UniqueProductEntity> list = uniqueProductService.getAllUniqueProductList();
        ReorderModel reorderModel = new ReorderModel();
        for(UniqueProductEntity product : list){
            if(product.getUniqueproductname().equalsIgnoreCase(purchaseItemModel.getProductname())){
                if(purchaseItemModel.getVendoruniquename().equals("null") || product.getUniquevendorname().equalsIgnoreCase(purchaseItemModel.getVendoruniquename())){
                    if(product.getCategory().equalsIgnoreCase(purchaseItemModel.getCategories())){

                        reorderModel.setProductStatus("REORDER");
                        reorderModel.setProductid(product.getProductid());
                        return reorderModel;
                    }
                }
            }
        }
        UniqueProductEntity productEntity = new UniqueProductEntity();
        productEntity.setUniqueproductname(purchaseItemModel.getProductname());
        productEntity.setCategory(purchaseItemModel.getCategories());
        productEntity.setBrandname(purchaseItemModel.getVendoruniquename());
        productEntity.setUniquevendorname(purchaseItemModel.getVendoruniquename());
        uniqueProductService.saveUniqueProduct(productEntity);
        reorderModel.setProductStatus("NEW");
        reorderModel.setProductid(111111111111L);
        return reorderModel;
    }


    @Override
    public PurchaseItemModel savePurchaseItem(PurchaseItemModel purchaseItemModel) {
        PurchaseItemEntity purchaseItemEntity = new PurchaseItemEntity();
        BeanUtils.copyProperties(purchaseItemModel,purchaseItemEntity);
        ReorderModel reorderModel = checkUniqueProduct(purchaseItemModel);
        purchaseItemEntity.setProductStatus(reorderModel.getProductStatus());
        purchaseItemEntity.setReorderproductid(reorderModel.getProductid());

        purchaseItemEntity.setCurrentdate(purchaseItemModel.getCurrentdate().substring(0,10));
        purchaseItemEntity.setExpectdate(purchaseItemModel.getExpectdate().substring(0,10));

        System.out.println(purchaseItemEntity.getExpectdate());
        String taxamount = String.format("%.2f",Double.parseDouble(purchaseItemModel.getTaxamount()));
        String totalamount = String.format("%.2f",Double.parseDouble(purchaseItemModel.getTotal()));
        purchaseItemEntity.setTaxamount(taxamount);
        purchaseItemEntity.setTotal(totalamount);
        purchaseItemEntity.setOrderstatus("Not Ordered");
        purchaseItemEntity.setBillstatus("No Bills");

        purchaseRepositary.save(purchaseItemEntity);

        long id = purchaseItemEntity.getPo_id();
        purchaseItemModel.setId(id);
        purchaseItemModel.setPurchaseid("PID-0"+id);
        purchaseItemEntity.setPurchaseid(purchaseItemModel.getPurchaseid());

        purchaseRepositary.save(purchaseItemEntity);

        pdfService.generatePdf(purchaseItemModel);

        return purchaseItemModel;
    }

    @Override
    public List<PurchaseItemModel> getAllPurchaseItemList() {
        List<PurchaseItemEntity> purchaseItemEntityList = new ArrayList<>();
        purchaseItemEntityList = purchaseRepositary.findAll();

        List<PurchaseItemModel> purchaseItemModelList = new ArrayList<>();

        for (PurchaseItemEntity purchaseItemEntity : purchaseItemEntityList) {
            PurchaseItemModel purchaseItemModel = new PurchaseItemModel();


            purchaseItemModel.setId(purchaseItemEntity.getPo_id());
            purchaseItemModel.setAmount(purchaseItemEntity.getAmount());
            purchaseItemModel.setBuyprice(purchaseItemEntity.getBuyprice());
            purchaseItemModel.setCurrentdate(purchaseItemEntity.getCurrentdate());
            purchaseItemModel.setExpectdate(purchaseItemEntity.getExpectdate());
            purchaseItemModel.setProductname(purchaseItemEntity.getProductname());
            purchaseItemModel.setCategories(purchaseItemEntity.getCategories());
            purchaseItemModel.setPurchaseid(purchaseItemEntity.getPurchaseid());
            purchaseItemModel.setQuantity(purchaseItemEntity.getQuantity());
            purchaseItemModel.setSubtotal(purchaseItemEntity.getSubtotal());
            purchaseItemModel.setTax(purchaseItemEntity.getTax());
            purchaseItemModel.setTotal(purchaseItemEntity.getTotal());
            purchaseItemModel.setVendoruniquename(purchaseItemEntity.getVendoruniquename());
            purchaseItemModel.setOrderstatus(purchaseItemEntity.getOrderstatus());
            purchaseItemModel.setBillstatus(purchaseItemEntity.getBillstatus());
            purchaseItemModel.setReorderproductid(purchaseItemEntity.getReorderproductid());

            purchaseItemModelList.add(purchaseItemModel);
        }


        return purchaseItemModelList;
    }

    @Override
    public Optional<PurchaseItemEntity> getById(Long id) {
        return purchaseRepositary.findById(id);
    }

    @Override
    public PurchaseItemModel getByPurchaseId(String purchaseid) {
        PurchaseItemEntity entity = new PurchaseItemEntity();
        PurchaseItemModel model = new PurchaseItemModel();
        entity = purchaseRepositary.findByPurchaseid(purchaseid);
        BeanUtils.copyProperties(entity,model);
        return model;
    }

    // Below code is refered from the youtube video. because this code is also predefined so we need to write the correct
// https://www.youtube.com/watch?v=hm23MfVnkCU&t=1358s&ab_channel=LearnCodeWithDurgesh
    @Override
    public void sendMail() throws FileNotFoundException {
        Gmailsender gmailsender = new Gmailsender();

        String from ="kunaldhavle9@gmail.com";
        String to="krunaldhavle1999@gmail.com";
        String subject ="sending email from device";
        String text ="sda";
        File file = new File("D:\\sa.pdf");

        boolean a = gmailsender.sendGmail(to, from, subject, text, file);
        if(a){
            System.out.println("email send");
        }else{
            System.out.println("email not send");
        }
    }

    // Below code is refered from the youtube video. because this code is also predefined so we need to write the correct
// https://www.youtube.com/watch?v=hm23MfVnkCU&t=1358s&ab_channel=LearnCodeWithDurgesh

    @Override
    public EmailSendData sendMailusingDetails(EmailSendData emailSendData) {
        Gmailsender gmailsender = new Gmailsender();
        String to = emailSendData.getTo();
        String from = emailSendData.getFrom();
        String subject = emailSendData.getSubject();
        String text = emailSendData.getContent();
        File file = new File("D:\\"+ emailSendData.getPurchaseid()+".pdf");

        boolean a = gmailsender.sendGmail(to, from, subject, text, file);
        if(a){
            System.out.println("email send");
        }else{
            System.out.println("email not send");
        }



        return emailSendData;
    }

    @Override
    public String updateOrderStatus(String purchaseid, String value) {
        PurchaseItemEntity entity = new PurchaseItemEntity();

        entity = purchaseRepositary.findByPurchaseid(purchaseid);
        value = value.replaceFirst("=$", "");


        if(value.equals("RECEIVED")){
            LocalDate date = LocalDate.now();

            ReceieveItemEnity receieveItemEnity = new ReceieveItemEnity();
            receieveItemEnity.setUsestatus(false);
            receieveItemEnity.setPurchaseid(purchaseid);
            receieveItemEnity.setQuantity(entity.getQuantity());
            receieveItemEnity.setProductname(entity.getProductname());
            receieveItemEnity.setOrderstatus(value);
            receieveItemEnity.setRoid("ROID-0"+ entity.getPo_id());
            receieveItemEnity.setReceiveddate(date.toString());
            receieveItemEnity.setProductstatus(entity.getProductStatus());
            receieveItemEnity.setVendoruniquename(entity.getVendoruniquename());
            entity.setOrderstatus(value);
            entity.setReceieveItemEnity(receieveItemEnity);

        }else if(value.equals("ORDERED")){
            entity.setOrderstatus(value);
            entity.setBillstatus("UNPAID");

        }else{
            LocalDate currentDate = LocalDate.now();

            BillEnity billEnity = new BillEnity();
            entity.setBillstatus("PAID");
            billEnity.setBill_id("BID-0"+entity.getPo_id());
            billEnity.setAmount(entity.getAmount());
            billEnity.setProductname(entity.getProductname());
            billEnity.setVendorname(entity.getVendoruniquename());
            billEnity.setBillstatus("BILLED");
            billEnity.setPaidstatus("PAID");
            billEnity.setPaymenttype(value);
            billEnity.setPaymentdate(currentDate.toString());
            entity.setBillEnity(billEnity);
        }

        purchaseRepositary.save(entity);
        return purchaseid;
    }

    @Override
    public String getNewPurchaseId(String id) {
        PurchaseItemModel purchaseItemModel = getByPurchaseId(id);

        PurchaseItemModel savePurchaeModel = new PurchaseItemModel();
        savePurchaeModel.setAmount(purchaseItemModel.getAmount());
        savePurchaeModel.setBuyprice(purchaseItemModel.getBuyprice());
        savePurchaeModel.setCategories(purchaseItemModel.getCategories());

        savePurchaeModel.setProductname(purchaseItemModel.getProductname());
        savePurchaeModel.setQuantity(purchaseItemModel.getQuantity());
        savePurchaeModel.setSubtotal(purchaseItemModel.getSubtotal());
        savePurchaeModel.setTax(purchaseItemModel.getTax());
        savePurchaeModel.setTaxamount(purchaseItemModel.getTaxamount());
        savePurchaeModel.setTotal(purchaseItemModel.getTotal());
        savePurchaeModel.setVendoruniquename(purchaseItemModel.getVendoruniquename());

        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plus(3, ChronoUnit.DAYS);

        savePurchaeModel.setCurrentdate(currentDate.toString());
        savePurchaeModel.setExpectdate(futureDate.toString());

        PurchaseItemModel savedPurchaseModel = savePurchaseItem(savePurchaeModel);

        return savedPurchaseModel.getPurchaseid();
    }
}
