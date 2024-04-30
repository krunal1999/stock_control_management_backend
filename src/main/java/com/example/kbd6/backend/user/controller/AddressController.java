package com.example.kbd6.backend.user.controller;

import com.example.kbd6.backend.user.entity.Address;
import com.example.kbd6.backend.user.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/address")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/save")
    public Address saveAddress(@RequestBody Address address){
        return addressService.saveAddress(address);
    }

    @GetMapping("/getaddress/{id}")
    public Address getAllAddressById(@PathVariable Long id){
        return addressService.getAllAddressById(id);
    }

}
