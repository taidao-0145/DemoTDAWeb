package com.example.demotda.controller.admin;

import com.example.demotda.model.Supplier;
import com.example.demotda.repositorie.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addsupplier")
public class AddSupplierController {
    private SupplierRepo supplierRepo;
    @Autowired
    public AddSupplierController(SupplierRepo supplierRepo){
        this.supplierRepo=supplierRepo;
    }

    @GetMapping
    public String addsup(){
        return "admin/addsupplier";
    }

    @PostMapping
    public String addsupp(@RequestParam("supplier") String sup,@RequestParam("address") String add){
        Supplier s= new Supplier(sup,add);
        supplierRepo.save(s);
        return "redirect:/addproduct";

    }
}
