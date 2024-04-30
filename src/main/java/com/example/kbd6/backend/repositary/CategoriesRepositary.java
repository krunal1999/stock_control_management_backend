package com.example.kbd6.backend.repositary;

import com.example.kbd6.backend.entity.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepositary extends JpaRepository<CategoriesEntity, Long> {
}
