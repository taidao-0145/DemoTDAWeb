package com.example.demotda.controller;

import com.example.demotda.model.Supplier;
import com.example.demotda.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SupplierController {
    private SupplierService supplierService;
    @Autowired
    public SupplierController(SupplierService supplierService){
        this.supplierService=supplierService;
    }

    @GetMapping("/addSupplier")
    public String viewAdd(){
        return "admin/addsupplier";
    }

    @PostMapping("/addSupplier")
    public String addSupplier(@RequestParam("supplier") String sup, @RequestParam("address") String add){
        Supplier s= new Supplier(sup,add);
        supplierService.saveSupplier(s);
        return "redirect:/addProduct";

    }
}
