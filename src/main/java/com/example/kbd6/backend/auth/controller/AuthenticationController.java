package com.example.kbd6.backend.auth.controller;

import com.example.kbd6.backend.auth.model.ApplicationUser;
import com.example.kbd6.backend.auth.model.LoginResponseDTO;
import com.example.kbd6.backend.auth.model.RegisterDto;
import com.example.kbd6.backend.auth.service.AuthenticationService;
import com.example.kbd6.backend.exception.errorclass.UserEmailAlreadyExistException;
import com.example.kbd6.backend.exception.errorclass.UsernameOrPasswordIsIncoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// code is taken from this video. code is predefined to work as required.
// https://www.youtube.com/watch?v=TeBt0Ike_Tk&ab_channel=UnknownKoder
// https://github.com/unknownkoder/spring-security-login-system
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegisterDto body) throws UserEmailAlreadyExistException {
        return authenticationService.registerUser(body);
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegisterDto body) throws UsernameOrPasswordIsIncoException {
        System.out.println(body);
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }



}
