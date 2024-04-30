package com.example.kbd6.backend.service;

import com.example.kbd6.backend.entity.VendorEntity;
import com.example.kbd6.backend.exception.errorclass.DuplicateVendorNameException;
import com.example.kbd6.backend.model.VendorModel;

import java.util.List;

public interface VendorService {
    VendorModel saveVendor(VendorModel vendorModel) throws DuplicateVendorNameException;

    List<VendorModel>  getAllVendor();

    VendorEntity getVendorByVendoruniquename(String name);

    VendorModel getVendorById(Long id);

    String changeVendorStatus(long id);

    String deleteVendor(long id);

    VendorEntity updateVendor(String name, VendorEntity vendorEntity);
}
