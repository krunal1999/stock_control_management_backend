package com.example.kbd6.backend.auth.repo;

import com.example.kbd6.backend.auth.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<ApplicationUser,Long> {

    Optional<ApplicationUser> findByUsername(String username);


    boolean existsByUsername(String username);
}
