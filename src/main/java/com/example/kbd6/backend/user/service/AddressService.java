package com.example.kbd6.backend.user.service;

import com.example.kbd6.backend.user.entity.Address;
import com.example.kbd6.backend.user.repo.AddressRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepo addressRepo;
    public Address saveAddress(Address address) {
        List<Address> addressList = addressRepo.findAll();
        for(Address address1:addressList){
            if(address1.getUserId() != null){
                if(address1.getUserId().equals(address.getUserId())){
                    addressRepo.delete(address1);
                }
            }
        }
        return addressRepo.save(address);
    }

    public Address getAllAddressById(Long id) {
        Address address = addressRepo.findByUserId(id);
        return address;
    }
}
