package com.example.kbd6.backend.user.repo;

import com.example.kbd6.backend.user.entity.OrderSuceess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSuccessRepo extends JpaRepository<OrderSuceess, Long> {
}
