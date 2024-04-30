package com.example.kbd6.backend.excel.helper;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class ExcelController {

    private final  ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/productdata")
    public ResponseEntity<Resource> productdownloadExcel() throws IOException {
        String filename = "product.xlsx";

        ByteArrayInputStream actualData = excelService.getProductData();
        InputStreamResource file = new InputStreamResource(actualData);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .body(file);
    }
    @GetMapping("/categorydata")
    public ResponseEntity<Resource> categorydownloadExcel() throws IOException {
        String filename = "category.xlsx";

        ByteArrayInputStream actualData = excelService.getCategoryData();
        InputStreamResource file = new InputStreamResource(actualData);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/ordersuccessdata")
    public ResponseEntity<Resource> orderSuccessdownloadExcel() throws IOException {
        String filename = "ordersuccess.xlsx";

        ByteArrayInputStream actualData = excelService.getOrdersuccessData();

        InputStreamResource file = new InputStreamResource(actualData);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/billdata")
    public ResponseEntity<Resource> billdatadownloadExcel() throws IOException {
        String filename = "bills.xlsx";

        ByteArrayInputStream actualData = excelService.getBillsData();

        InputStreamResource file = new InputStreamResource(actualData);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/purchaseitemdata")
    public ResponseEntity<Resource> purchaseitemdatadownloadExcel() throws IOException {
        String filename = "purchaseitem.xlsx";

        ByteArrayInputStream actualData = excelService.getPurchaseitemdata();

        InputStreamResource file = new InputStreamResource(actualData);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/vendordata")
    public ResponseEntity<Resource> vendordatadownloadExcel() throws IOException {
        String filename = "vendordata.xlsx";

        ByteArrayInputStream actualData = excelService.getVendordatadata();

        InputStreamResource file = new InputStreamResource(actualData);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/userdata")
    public ResponseEntity<Resource> userdatadownloadExcel() throws IOException {
        String filename = "userdata.xlsx";

        ByteArrayInputStream actualData = excelService.getUserdatadata();

        InputStreamResource file = new InputStreamResource(actualData);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }


}
