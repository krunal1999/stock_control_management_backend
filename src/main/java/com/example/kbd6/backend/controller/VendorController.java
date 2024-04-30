package com.example.kbd6.backend.controller;

import com.example.kbd6.backend.entity.VendorEntity;
import com.example.kbd6.backend.exception.errorclass.DuplicateVendorNameException;
import com.example.kbd6.backend.model.VendorModel;
import com.example.kbd6.backend.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/vendor")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @PostMapping("/new")
    public VendorModel saveVendor(@RequestBody VendorModel vendorModel) throws DuplicateVendorNameException {
        return vendorService.saveVendor(vendorModel);

    }
    @GetMapping("/getallvendor")
    public List<VendorModel>  getAllVendor(){
        return vendorService.getAllVendor();
    }

    @GetMapping("/getevndorbyid/{id}")
    public VendorModel getVendorById(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @GetMapping("/getvendorbydisplayname/{name}")
    public VendorEntity getVendorByDisplayName(@PathVariable String name){
        return vendorService.getVendorByVendoruniquename(name);
    }

    @PutMapping("/{id}")
    public String changeVendorStatus(@PathVariable long id){
        return vendorService.changeVendorStatus(id);
    }

    @DeleteMapping("/{id}")
    public String deleteVendor(@PathVariable long id){
        return vendorService.deleteVendor(id);
    }

    @PostMapping("/updatevendor/{name}")
    public VendorEntity updateVendor(@PathVariable String name , @RequestBody VendorEntity vendorEntity){
        VendorEntity vendorEntity1 = getVendorByDisplayName(name);
        if(vendorEntity1 != null){
            vendorEntity1.setVendoruniquename(name);
            try{
                return vendorService.updateVendor(name,vendorEntity);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;

    }

}
