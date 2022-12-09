package com.example.demotda.controller.admin;

import com.example.demotda.repositorie.OderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ship")
public class ShippingController {
    private OderRepo oderRepo;
    @Autowired
    public ShippingController(OderRepo oderRepo){
        this.oderRepo=oderRepo;
    }

    @GetMapping
    public String shipping(@RequestParam("id") Long id){
        oderRepo.shiploder(id);
        return "redirect:/oders";
    }

}
