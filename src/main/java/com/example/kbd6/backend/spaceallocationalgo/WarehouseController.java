package com.example.kbd6.backend.spaceallocationalgo;

import com.example.kbd6.backend.spaceallocationalgo.model.Location;
import com.example.kbd6.backend.spaceallocationalgo.model.Product;
import com.example.kbd6.backend.spaceallocationalgo.service.Warehouse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin("http://localhost:3000/")
public class WarehouseController {

    private final Warehouse warehouse;

    public WarehouseController(Warehouse warehouse) {
        this.warehouse = warehouse;
    }


    @GetMapping("/get-all-locations")
    public Location[] getAllLocations() {
        return warehouse.getAllLocations();
    }


    @PostMapping("/add-product")
    public Location[] addProducts(@RequestBody Product[] products) {
        return warehouse.addProducts(products);
    }

    @GetMapping("/demo")
    public List<Integer> getList(){
        List<Integer> list = new ArrayList<>();
        list.add(120);
        list.add(15);
        list.add(120);
        list.add(140);
        list.add(120);
        list.add(120);
        list.add(20);
        list.add(120);
        list.add(120);
        return list;
    }


}
