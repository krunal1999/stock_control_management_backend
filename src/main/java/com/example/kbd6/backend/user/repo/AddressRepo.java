package com.example.kbd6.backend.user.repo;

import com.example.kbd6.backend.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

    Address findByUserId(Long id);
}
