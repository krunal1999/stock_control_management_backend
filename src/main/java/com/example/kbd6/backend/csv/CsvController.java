package com.example.kbd6.backend.csv;

import com.example.kbd6.backend.entity.BillEnity;
import com.example.kbd6.backend.entity.CategoriesEntity;
import com.example.kbd6.backend.entity.PurchaseItemEntity;
import com.example.kbd6.backend.entity.VendorEntity;
import com.example.kbd6.backend.entity.inventory.ProductEntity;
import com.example.kbd6.backend.model.ProductCsvDTO;
import com.example.kbd6.backend.repositary.*;
import com.example.kbd6.backend.user.entity.Address;
import com.example.kbd6.backend.user.entity.OrderSuceess;
import com.example.kbd6.backend.user.repo.AddressRepo;
import com.example.kbd6.backend.user.repo.OrderSuccessRepo;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class CsvController {
// this code is referred from below website. It is predefined code need to use to convert into csv file format
//    https://simplifyingtechcode.wordpress.com/2022/11/06/how-to-export-data-to-csv-using-springboot-opencsv/
    private final ProductRepositary productRepositary;
    private final CategoriesRepositary categoriesRepositary;
    private final OrderSuccessRepo orderSuccessRepo;
    private final BillRepositarty billRepositarty;
    private final PurchaseRepositary purchaseRepositary;
    private final VendorRepositary vendorRepositary;
    private final AddressRepo addressRepo;

    public CsvController(ProductRepositary productRepositary, CategoriesRepositary categoriesRepositary, OrderSuccessRepo orderSuccessRepo, BillRepositarty billRepositarty, PurchaseRepositary purchaseRepositary, VendorRepositary vendorRepositary, AddressRepo addressRepo) {
        this.productRepositary = productRepositary;
        this.categoriesRepositary = categoriesRepositary;
        this.orderSuccessRepo = orderSuccessRepo;
        this.billRepositarty = billRepositarty;
        this.purchaseRepositary = purchaseRepositary;
        this.vendorRepositary = vendorRepositary;
        this.addressRepo = addressRepo;
    }

    @GetMapping("/categorycsvexport")
    public void exportCSVCategory(HttpServletResponse response)
            throws Exception {
        String filename = "Category.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<CategoriesEntity> writer = new StatefulBeanToCsvBuilder<CategoriesEntity>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(true)
                .build();
        writer.write(categoriesRepositary.findAll());
    }

    @GetMapping("/ordersuccesscsvexport")
    public void exportCSVOrderSuccess(HttpServletResponse response)
            throws Exception {
        String filename = "OrderSuccess.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<OrderSuceess> writer = new StatefulBeanToCsvBuilder<OrderSuceess>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(true)
                .build();
        writer.write(orderSuccessRepo.findAll());
    }

    @GetMapping("/billscsvexport")
    public void exportCSVBills(HttpServletResponse response)
            throws Exception {
        String filename = "Bills.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<BillEnity> writer = new StatefulBeanToCsvBuilder<BillEnity>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(true)
                .build();
        writer.write(billRepositarty.findAll());
    }

    @GetMapping("/purchasecsvexport")
    public void exportCSVPurchase(HttpServletResponse response)
            throws Exception {
        String filename = "Purchase.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<PurchaseItemEntity> writer = new StatefulBeanToCsvBuilder<PurchaseItemEntity>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(true)
                .build();
        writer.write(purchaseRepositary.findAll());
    }

    @GetMapping("/vendorcsvexport")
    public void exportCSVVendor(HttpServletResponse response)
            throws Exception {
        String filename = "Vendor.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<VendorEntity> writer = new StatefulBeanToCsvBuilder<VendorEntity>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(true)
                .build();
        writer.write(vendorRepositary.findAll());
    }
    @GetMapping("/usercsvexport")
    public void exportCSVUser(HttpServletResponse response)
            throws Exception {
        String filename = "User.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<Address> writer = new StatefulBeanToCsvBuilder<Address>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(true)
                .build();
        writer.write(addressRepo.findAll());
    }

    @GetMapping("/productcsvexport")
    public void exportCSVProduct(HttpServletResponse response)
            throws Exception {
        String filename = "Product.csv";
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        List<ProductEntity> productEntityList = productRepositary.findAll();
        List<ProductCsvDTO> productCsvDTOList = new ArrayList<>();

        for(ProductEntity product: productEntityList){
            ProductCsvDTO productCsvDTO = new ProductCsvDTO();
            productCsvDTO.setId(product.getId());
            productCsvDTO.setProductid(product.getProductid());
            productCsvDTO.setVendoruniquename(product.getVendoruniquename());
            productCsvDTO.setReceieveItem(product.getReceieveItem());
            productCsvDTO.setProductname(product.getProductname());
            productCsvDTO.setBrand(product.getBrand());
            productCsvDTO.setCategories(product.getCategories());
            productCsvDTO.setAbout(product.getAbout());
            productCsvDTO.setTitle(product.getTitle());
            productCsvDTO.setBuyprice(product.getBuyprice());
            productCsvDTO.setSellingPrice(product.getSellingPrice());
            productCsvDTO.setQuantity(product.getQuantity());
            productCsvDTO.setRemainingquantity(product.getRemainingquantity());
            productCsvDTO.setSoldquantity(product.getSoldquantity());
            productCsvDTO.setMinimumQuantityAlert(product.getMinimumQuantityAlert());
            productCsvDTO.setAutoReorderEnabled(product.getAutoReorderEnabled());
            productCsvDTO.setLength(product.getLength());
            productCsvDTO.setBreadth(product.getBreadth());
            productCsvDTO.setHeight(product.getHeight());
            productCsvDTO.setVolume(product.getVolume());
            productCsvDTO.setActivestatus(product.getActivestatus());
            productCsvDTO.setProductstatus(product.getProductstatus());

            productCsvDTOList.add(productCsvDTO);
        }

        StatefulBeanToCsv<ProductCsvDTO> writer = new StatefulBeanToCsvBuilder<ProductCsvDTO>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(true)
                .build();
        writer.write(productCsvDTOList);
    }

}
