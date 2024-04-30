package com.example.kbd6.backend.user.controller;

import com.example.kbd6.backend.user.service.UserClientService;
import com.example.kbd6.backend.user.userDTO.UserProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user/homedashboard")
@RestController
@CrossOrigin("http://localhost:3000")
public class HomeDashboardController {

    private final UserClientService userService;


    public HomeDashboardController(UserClientService userService) {
        this.userService = userService;
    }

    @GetMapping("/getallproduct")
    public List<UserProductDto> getallproduct(){
        return userService.getallproduct();

    }

    @GetMapping("/getproduct/{id}")
    public UserProductDto getProductById(@PathVariable Long id){
        return userService.getProductById(id);

    }
}
