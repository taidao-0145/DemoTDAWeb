package com.example.demotda.controller.admin;

import com.example.demotda.repositorie.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private ProductRepo productRepo;
    @Autowired
    public AdminController(ProductRepo productRepo){
        this.productRepo=productRepo;
    }
    @GetMapping
    public String admin(){


        return "admin/admin";
    }
}
