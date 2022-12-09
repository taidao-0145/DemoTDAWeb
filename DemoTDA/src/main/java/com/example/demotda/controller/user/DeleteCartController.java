package com.example.demotda.controller.user;

import com.example.demotda.repositorie.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/deletecart")
public class DeleteCartController {
    private CartRepo cartRepo;
    @Autowired
    public DeleteCartController(CartRepo cartRepo){
        this.cartRepo=cartRepo;
    }
    @GetMapping
    public String deletecart(@RequestParam("id") Long id){
        cartRepo.deleteById(id);
        return "redirect:/viewcart";
    }
}
