package com.example.kbd6.backend.auth.service;

import com.example.kbd6.backend.auth.model.ApplicationUser;
import com.example.kbd6.backend.auth.model.LoginResponseDTO;
import com.example.kbd6.backend.auth.model.RegisterDto;
import com.example.kbd6.backend.auth.model.Role;
import com.example.kbd6.backend.auth.repo.RoleRepo;
import com.example.kbd6.backend.auth.repo.UserRepo;

import com.example.kbd6.backend.exception.errorclass.UserEmailAlreadyExistException;
import com.example.kbd6.backend.exception.errorclass.UsernameOrPasswordIsIncoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

// code is taken from this video. code is predefined to work as required.
// https://www.youtube.com/watch?v=TeBt0Ike_Tk&ab_channel=UnknownKoder
// https://github.com/unknownkoder/spring-security-login-system

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(RegisterDto body) throws UserEmailAlreadyExistException {
    try {

        String encodePassword = passwordEncoder.encode(body.getPassword());
        Role userRole = roleRepo.findByAuthority("USER").orElseThrow(() -> new RuntimeException("no user found"));

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        if(userRepo.existsByUsername(body.getUsername())) {
            throw new UserEmailAlreadyExistException("user email id i already used");
        }

        return userRepo.save(new ApplicationUser(0, body.getUsername(), encodePassword, body.getFirstname(), body.getLastname(), authorities));

    }catch (UserEmailAlreadyExistException e){
        throw new UserEmailAlreadyExistException();
    }
        }

    public LoginResponseDTO loginUser(String username , String password) throws UsernameOrPasswordIsIncoException {

        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            System.out.println(auth);
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDTO(userRepo.findByUsername(username).orElseThrow(()-> new UsernameOrPasswordIsIncoException("no user found")), token);

        }catch (AuthenticationException e){
            throw new UsernameOrPasswordIsIncoException("UserName or Password Is inCorrect");
//            return new LoginResponseDTO(null , "");
        }

    }


}
