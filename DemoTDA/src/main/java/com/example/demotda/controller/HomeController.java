package com.example.demotda.controller;

import com.example.demotda.model.Product;
import com.example.demotda.model.User;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")

public class HomeController {
    private ProductRepo productRepo;
    private UserRepo userRepo;
    @Autowired
    public HomeController(ProductRepo productRepo,UserRepo userRepo){
        this.userRepo= userRepo;
        this.productRepo=productRepo;
    }

    @GetMapping
    public String home(Model model){
            List<Product> listnew= productRepo.listnew();
            model.addAttribute("listnew",listnew);
            List<Product> listsale= productRepo.listsale();
            model.addAttribute("listsale",listsale);
        return "home";
    }
}
