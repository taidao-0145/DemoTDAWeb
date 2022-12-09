package com.example.demotda.controller.user;

import com.example.demotda.repositorie.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreController {
    private ProductRepo productRepo;

    @Autowired
    public StoreController(ProductRepo productRepo){
        this.productRepo=productRepo;
    }
    @GetMapping
    public String viewstore(){

        return "user/store";
    }
}
