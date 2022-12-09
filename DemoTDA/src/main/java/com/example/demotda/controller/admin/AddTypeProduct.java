package com.example.demotda.controller.admin;

import com.example.demotda.model.TypeProduct;
import com.example.demotda.repositorie.TypeProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller@RequestMapping("/addtypeproduct")
public class AddTypeProduct {
    private TypeProductRepo typeProductRepo;
    @Autowired
    public AddTypeProduct(TypeProductRepo typeProductRepo){
        this.typeProductRepo=typeProductRepo;
    }

    @GetMapping
    public String formadd(){

        return "admin/addtypeproduct";
    }
    @PostMapping
    public String addtype(@ModelAttribute TypeProduct typeProduct){
        typeProductRepo.save(typeProduct);

        return "redirect:/addproduct";
    }
}
