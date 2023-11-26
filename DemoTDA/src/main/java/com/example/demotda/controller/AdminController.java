package com.example.demotda.controller;

import com.example.demotda.model.Oder;
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
@RequestMapping("/admin/index")
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
        List<Product> listHetSach=productService.outOfStock();
        model.addAttribute("listHet0",listHetSach);
        List<Product>  listInventory= productService.checkInventory();
        model.addAttribute("listInventory",listInventory);
        Date date = new Date();
        String today = new SimpleDateFormat("dd/MM/yyyy").format(date.getTime());
        String today1 = new SimpleDateFormat("yyyy/MM/dd").format(date.getTime());
        model.addAttribute("today",today);
        long countOder= oderService.countOder();
        model.addAttribute("countOder",countOder);
        Long revenueYesterday= productSoldService.revenueYesterday(today1,today1);
        if(revenueYesterday==null){
            revenueYesterday= Long.valueOf(0);
        }
        String newNumber=String.format("%,d",revenueYesterday);
        model.addAttribute("revenueYesterday",newNumber);
        long countOderShip= oderService.countOderShip();
        model.addAttribute("countOderShip",countOderShip);
        List<Oder> oderNew=oderService.oderNew(today1);
        model.addAttribute("oderNew",oderNew);
        return "admin/admin";
    }
}
