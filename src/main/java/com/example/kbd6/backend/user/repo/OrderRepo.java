package com.example.kbd6.backend.user.repo;

import com.example.kbd6.backend.user.entity.PlaceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<PlaceOrder, Long> {
    Optional<Object> findByUsername(Long id);

    boolean existsByUsername(Long username);

    void deleteByUsername(Long username);

}
