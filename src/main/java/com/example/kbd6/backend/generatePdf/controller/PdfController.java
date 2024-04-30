package com.example.kbd6.backend.generatePdf.controller;

import com.example.kbd6.backend.generatePdf.service.PdfService;
import com.example.kbd6.backend.model.PurchaseItemModel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/getpdf")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }


    @GetMapping("/openpdf")
    public ResponseEntity<InputStreamResource> createPdf(PurchaseItemModel purchaseItemModel){

        ByteArrayInputStream pdf = pdfService.createPdf(purchaseItemModel);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline;file=lcwd.pdf");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));

    }

    @GetMapping("/downloadpdf")
    public void generatePdf(PurchaseItemModel purchaseItemModel)  {
        pdfService.generatePdf( purchaseItemModel);
    }

}
