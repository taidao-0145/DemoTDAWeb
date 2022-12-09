package com.example.demotda.controller.admin;

import com.example.demotda.repositorie.OderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/doneship")
public class DoneShippingController {
    private OderRepo oderRepo;

    @Autowired
    public DoneShippingController(OderRepo oderRepo){
        this.oderRepo=oderRepo;
    }
    @GetMapping
    public String doneship(@RequestParam("id") Long id){
        oderRepo.doneship(id);
        return "redirect:/oders";
    }
}
