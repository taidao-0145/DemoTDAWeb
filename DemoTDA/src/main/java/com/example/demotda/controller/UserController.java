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

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('User')")
public class UserController {
    private ProductRepo productRepo;
    private UserRepo userRepo;
    @Autowired
    public UserController(ProductRepo productRepo,UserRepo userRepo){
        this.userRepo= userRepo;
        this.productRepo=productRepo;
    }
    @GetMapping
    public String viewuser(Model model,Principal principal){
        if(principal==null){
            return "login";
        }
        User u = userRepo.findUserByUsername( principal.getName());
        List<Product> listnew= productRepo.listnew();
        List<Product> listsale= productRepo.listsale();
        model.addAttribute("listsale",listsale);
        model.addAttribute("listnew",listnew);
        model.addAttribute("username",u.getUsername());
        System.err.println(principal.getName());
        return "user/user";
    }
}
