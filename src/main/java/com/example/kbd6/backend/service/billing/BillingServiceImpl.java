package com.example.kbd6.backend.service.billing;

import com.example.kbd6.backend.entity.BillEnity;
import com.example.kbd6.backend.model.BillModel;
import com.example.kbd6.backend.repositary.BillRepositarty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillingServiceImpl implements BillingService{

    private final BillRepositarty billRepositary;

    public BillingServiceImpl(BillRepositarty billRepositary) {
        this.billRepositary = billRepositary;
    }

    @Override
    public List<BillModel> getAllBillingList() {
        List<BillEnity> billEnity = new ArrayList<>();
        List<BillModel> billModels = new ArrayList<>();

        billEnity = billRepositary.findAll();

        for (BillEnity bill : billEnity){
            BillModel model = new BillModel();
            model.setBill_id(bill.getBill_id());
            model.setBillstatus(bill.getBillstatus());
            model.setPaymentdate(bill.getPaymentdate());
            model.setPaymenttype(bill.getPaymenttype());
            model.setAmount(bill.getAmount());
            model.setPaidstatus(bill.getPaidstatus());
            model.setProductname(bill.getProductname());
            model.setVendorname(bill.getVendorname());
            model.setId(bill.getId());
            billModels.add(model);
        }
        return billModels;
    }
}
