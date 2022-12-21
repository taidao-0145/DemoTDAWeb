package com.example.demotda.controller.Login;

import com.example.demotda.model.Product;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.repositorie.UserRepo;
import com.example.demotda.service.ProductService;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")

public class HomeController {
    private ProductService productService;
    private UserService userService;
    @Autowired
    public HomeController(ProductService productService,UserService userService){
        this.userService= userService;
        this.productService=productService;
    }
    @GetMapping
    public String home(Model model){
            List<Product> listNew= productService.listNew();
            model.addAttribute("listNew",listNew);
            List<Product> listSale= productService.listSale();
            model.addAttribute("listSale",listSale);
        return "login/home";
    }
}
