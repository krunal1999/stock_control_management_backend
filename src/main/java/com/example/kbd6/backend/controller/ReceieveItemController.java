package com.example.kbd6.backend.controller;

import com.example.kbd6.backend.entity.ReceieveItemEnity;
import com.example.kbd6.backend.model.PurchaseItemModel;
import com.example.kbd6.backend.model.ReceieveditemModel;
import com.example.kbd6.backend.service.receieveitem.ReceieveItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/receieve")
@CrossOrigin(origins = "http://localhost:3000")
public class ReceieveItemController {

    private final ReceieveItemService receieveItemService;

    public ReceieveItemController(ReceieveItemService receieveItemService) {
        this.receieveItemService = receieveItemService;
    }

    @GetMapping("/getlist")
    public List<ReceieveItemEnity> getAllList(){
        return receieveItemService.getAllList();
    }


    @PostMapping("/changethestatus/{roid}")
    public void changeStatusToUsed(@PathVariable String roid){
        receieveItemService.changetheStatusToUsed(roid);

    }




}
