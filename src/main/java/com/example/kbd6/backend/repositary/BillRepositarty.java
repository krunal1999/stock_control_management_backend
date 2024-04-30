package com.example.kbd6.backend.repositary;

import com.example.kbd6.backend.entity.BillEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepositarty extends JpaRepository<BillEnity , Long> {
}
