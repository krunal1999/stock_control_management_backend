package com.example.kbd6.backend;

import com.example.kbd6.backend.auth.model.ApplicationUser;
import com.example.kbd6.backend.auth.model.Role;
import com.example.kbd6.backend.auth.repo.RoleRepo;
import com.example.kbd6.backend.auth.repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);


	}
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		return new MultipartResolver() {
			@Override
			public boolean isMultipart(HttpServletRequest request) {
				return false;
			}

			@Override
			public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
				return null;
			}

			@Override
			public void cleanupMultipart(MultipartHttpServletRequest request) {

			}
		};

	}

	@Bean
	CommandLineRunner run(RoleRepo roleRepo, UserRepo userRepo , PasswordEncoder passwordEncoder){
		return args -> {
			if(roleRepo.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole = roleRepo.save(new Role("ADMIN"));
			roleRepo.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);


			ApplicationUser admin = new ApplicationUser(2,"admin@admin.com",passwordEncoder.encode("Admin@123"),"krunal","dhavle",roles);
			userRepo.save(admin);

		};
	}

}
