package com.example.kbd6.backend.repositary;

import com.example.kbd6.backend.entity.inventory.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositary extends JpaRepository<ProductEntity, Long> {



}
