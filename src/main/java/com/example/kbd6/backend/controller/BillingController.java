package com.example.kbd6.backend.controller;

import com.example.kbd6.backend.model.BillModel;
import com.example.kbd6.backend.service.billing.BillingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/billing")
@CrossOrigin(origins = "http://localhost:3000")
public class BillingController {

    private final BillingService billingService;


    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping("getlist")
    public List<BillModel> getAllBillList(){
        return billingService.getAllBillingList();
    }
}
