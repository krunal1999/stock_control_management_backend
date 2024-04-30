package com.example.kbd6.backend.repositary;

import com.example.kbd6.backend.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepositary extends JpaRepository<VendorEntity , Long> {
    VendorEntity findByVendoruniquename(String displayname);

}
