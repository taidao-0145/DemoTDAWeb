package com.example.demotda.controller;

import com.example.demotda.model.Product;
import com.example.demotda.repositorie.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/viewproduct")
public class ViewProductController {
    private ProductRepo productRepo;
    @Autowired
    public ViewProductController(ProductRepo productRepo){
        this.productRepo=productRepo;
    }

    @GetMapping
    public String viewproduct(@RequestParam("id") Long id, Model model){
        Product product= productRepo.getById(id);
        model.addAttribute("product",product);

        return "user/viewproduct";
    }
}
