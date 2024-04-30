package com.example.kbd6.backend.repositary;

import com.example.kbd6.backend.entity.ReceieveItemEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceieveItemRepositary extends JpaRepository<ReceieveItemEnity, Long> {

    ReceieveItemEnity  findByRoid(String ro_id);


}
