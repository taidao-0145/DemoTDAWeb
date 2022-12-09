package com.example.demotda.controller.admin;

import com.example.demotda.model.Product;
import com.example.demotda.repositorie.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class DeleteProductController {
    private ProductRepo productRepo;
    @Autowired
    public DeleteProductController(ProductRepo productRepo){
        this.productRepo=productRepo;
    }

    @GetMapping("/deleteproduct")
    public String viewdelete(@RequestParam("id") Long id, Model model){
        Product product= productRepo.getById(id);
        model.addAttribute("product", product);
        return "admin/deleteproduct";
    }
    @GetMapping("/confirmdelete")
    public String confirmdelete(@RequestParam("id") Long id){
        productRepo.deleteById(id);
        return "redirect:/productadmin";
    }
}
