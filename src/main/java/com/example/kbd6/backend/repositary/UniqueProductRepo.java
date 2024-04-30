package com.example.kbd6.backend.repositary;

import com.example.kbd6.backend.entity.UniqueProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniqueProductRepo extends JpaRepository<UniqueProductEntity, Long> {
}
