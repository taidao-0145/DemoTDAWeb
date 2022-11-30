package com.example.demotda.controller;


import com.example.demotda.model.Cart;
import com.example.demotda.model.Product;
import com.example.demotda.repositorie.CartRepo;
import com.example.demotda.repositorie.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/addcart")
public class AddToCartController {
    private ProductRepo productRepo;
    private CartRepo cartRepo;

    @Autowired
    public AddToCartController(ProductRepo productRepo,CartRepo cartRepo){
        this.cartRepo=cartRepo;
        this.productRepo=productRepo;
    }
    @GetMapping
    public String addtocart(@RequestParam("id") Long id, Principal principal){
        String username=principal.getName();
        Product p= productRepo.getById(id);
        String nameproduct= p.getNameproduct();
        String img= p.getImg();
        int price=p.getPrice();
        int soluong= p.getSoluong();
        Cart cart= new Cart(username,id,nameproduct,img,price,1,price);
        if(soluong>0){
            if(cartRepo.getCartByUsernameAndIdproduct(username,id).isEmpty()){
                cartRepo.save(cart);
            }
            else{
                cartRepo.updatecheck(username,id);
            }

        }
        else {
            return "redirect:/user?err= san pham da het hang";
        }

        return "redirect:/viewcart?e=them vao gio hang thanh cong";
    }
}
