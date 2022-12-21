package com.example.demotda.controller;

import com.example.demotda.model.Product;
import com.example.demotda.service.OderService;
import com.example.demotda.service.ProductService;
import com.example.demotda.service.ProductSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private OderService oderService;
    private ProductService productService;
    private ProductSoldService productSoldService;
    @Autowired
    public AdminController(ProductService productService, OderService oderService,ProductSoldService productSoldService){
        this.oderService= oderService;
        this.productService=productService;
        this.productSoldService=productSoldService;

    }
    @GetMapping
    public String admin(Model model){
        List<Product> listHet=productService.listHet();
        model.addAttribute("listHet",listHet);
        List<Product>  listInventory= productService.checkInventory();
        model.addAttribute("listInventory",listInventory);
        Date date = new Date();
        String today = new SimpleDateFormat("dd/MM/yyyy").format(date.getTime());
        String today1 = new SimpleDateFormat("yyyy/MM/dd").format(date.getTime());
        model.addAttribute("today",today);
        long countOder= oderService.countOder();
        model.addAttribute("countOder",countOder);
        long revenueYesterday= productSoldService.revenueYesterday(today1,today1);
        model.addAttribute("revenueYesterday",revenueYesterday);
        long countOderShip= oderService.countOderShip();
        model.addAttribute("countOderShip",countOderShip);
        return "admin/admin";
    }
}
