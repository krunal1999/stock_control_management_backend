package com.example.kbd6.backend.service;

import com.example.kbd6.backend.entity.VendorEntity;
import com.example.kbd6.backend.exception.errorclass.DuplicateVendorNameException;
import com.example.kbd6.backend.model.VendorModel;
import com.example.kbd6.backend.repositary.VendorRepositary;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService{

    private final VendorRepositary vendorRepositary;

    public VendorServiceImpl(VendorRepositary vendorRepositary) {
        this.vendorRepositary = vendorRepositary;
    }

    @Override
    public VendorModel saveVendor(VendorModel vendorModel) throws DuplicateVendorNameException {

        VendorEntity vendorEntityCheck = getVendorByVendoruniquename(vendorModel.getVendoruniquename());
        if(vendorEntityCheck == null) {
            VendorEntity vendor = new VendorEntity();
            BeanUtils.copyProperties(vendorModel, vendor);
            vendor.setTitles(vendor.getTitles().toUpperCase());
            vendor.setFirstname(vendor.getFirstname().toUpperCase());
            vendor.setLastname(vendor.getLastname().toUpperCase());
            vendor.setBrandname(vendor.getBrandname().toUpperCase());
            vendor.setVendoruniquename(vendor.getVendoruniquename().toUpperCase());
            vendor.setLocation(vendor.getLocation().toUpperCase());
            vendor.setCountry(vendor.getCountry().toUpperCase());
            vendor.setActivestatus("ACTIVE");
            vendorRepositary.save(vendor);
            return vendorModel;
        }else{
            try {
                throw new DuplicateVendorNameException("Vendor Name Already exist");
            } catch (DuplicateVendorNameException e) {
                throw new DuplicateVendorNameException(e);
            }
        }

    }

    @Override
    public List<VendorModel>  getAllVendor() {
        List<VendorEntity> vendorEntities = new ArrayList<>();
        vendorEntities = vendorRepositary.findAll();
        List<VendorModel> models = new ArrayList<>();

        for (VendorEntity vendorEntity : vendorEntities) {
            VendorModel model = new VendorModel();

            model.setId(vendorEntity.getVendor_id());
            model.setFirstname(vendorEntity.getFirstname());
            model.setLastname(vendorEntity.getLastname());
            model.setEmail(vendorEntity.getEmail());
            model.setCountry(vendorEntity.getCountry());
            model.setLocation(vendorEntity.getLocation());
            model.setVendoruniquename(vendorEntity.getVendoruniquename());
            model.setPhonenumber(vendorEntity.getPhonenumber());
            model.setActivestatus(vendorEntity.getActivestatus());
            model.setTitles(vendorEntity.getTitles());
            model.setBrandname(vendorEntity.getBrandname());

            models.add(model);
        }

        return models;

    }

    @Override
    public VendorEntity getVendorByVendoruniquename(String name){

        return vendorRepositary.findByVendoruniquename(name);
    }

    @Override
    public VendorModel getVendorById(Long id) {
        Optional<VendorEntity> vendor = Optional.of(new VendorEntity());
        vendor = vendorRepositary.findById(id);
        VendorModel model = new VendorModel();
        BeanUtils.copyProperties(vendor, model );
        return model;
    }

    @Override
    public String changeVendorStatus(long id) {
        VendorEntity entity = vendorRepositary.findById(id).orElse(null);
        if(entity != null){
            if(entity.getActivestatus().equals("ACTIVE")) {
                entity.setActivestatus("INACTIVE");
                vendorRepositary.save(entity);
                return "ok";
            }else{
                entity.setActivestatus("ACTIVE");
                vendorRepositary.save(entity);
                return "ok";
            }
        }else{
            return "NOT UPDATED";
        }
    }

    @Override
    public String deleteVendor(long id) {
        VendorEntity entity = vendorRepositary.findById(id).orElse(null);
        if(entity != null){
            vendorRepositary.delete(entity);
            return "deleted";
        }else{
            return "NOT DELETED";
        }
    }

    @Override
    public VendorEntity updateVendor(String name, VendorEntity vendorEntity) {
        VendorEntity vendor = getVendorByVendoruniquename(name);
        if(vendor != null){
//            vendor.setVendor_id(vendorEntity.getVendor_id());
            vendor.setTitles(vendorEntity.getTitles());
            vendor.setFirstname(vendorEntity.getFirstname());
            vendor.setLastname(vendorEntity.getLastname());
            vendor.setVendoruniquename(vendorEntity.getVendoruniquename());
            vendor.setBrandname(vendorEntity.getBrandname());
            vendor.setEmail(vendorEntity.getEmail());
            vendor.setLocation(vendorEntity.getLocation());
            vendor.setPhonenumber(vendorEntity.getPhonenumber());
            vendor.setCountry(vendorEntity.getCountry());
            vendor.setActivestatus(vendorEntity.getActivestatus());
            return vendorRepositary.save(vendor);
        }

        return null;
    }


}
