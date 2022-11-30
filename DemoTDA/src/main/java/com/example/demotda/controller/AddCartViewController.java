package com.example.demotda.controller;

import com.example.demotda.repositorie.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("addcartview")
public class AddCartViewController {
    private CartRepo cartRepo;
    @Autowired
    public AddCartViewController(CartRepo cartRepo){
        this.cartRepo=cartRepo;
    }

    @GetMapping
    public String addcartview(){

        return "cart";
    }
}
