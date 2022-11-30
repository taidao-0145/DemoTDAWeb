package com.example.demotda.controller;

import com.example.demotda.model.Product;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class test {
    @Autowired
    private ProductRepo productRepo;
    private UserRepo userRepo;

    @GetMapping
    public String test(Model model){
        List<Product> listnew= productRepo.listnew();
        model.addAttribute("listnew",listnew);
        return "test";
    }
}
