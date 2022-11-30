package com.example.demotda.controller;

import com.example.demotda.model.Cart;
import com.example.demotda.model.Oder;
import com.example.demotda.model.Product;
import com.example.demotda.repositorie.CartRepo;
import com.example.demotda.repositorie.OderRepo;
import com.example.demotda.repositorie.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("checkout")
public class CheckOutController {
    private CartRepo cartRepo;
    private ProductRepo productRepo;
    private OderRepo oderRepo;
    @Autowired
    public CheckOutController(ProductRepo productRepo, CartRepo cartRepo,OderRepo oderRepo){
        this.productRepo=productRepo;
        this.cartRepo=cartRepo;
        this.oderRepo=oderRepo;
    }

    @GetMapping
    public String viewcheckout(@RequestParam("quantity") int quantity,
                               @RequestParam("idproduct") Long id,
                               @RequestParam("total") int total,
                               @RequestParam("idcart") int idcart,
                               Model model){
        Product p= productRepo.getById(id);
        String nameproduct=p.getNameproduct();
        model.addAttribute("quantity", quantity);
        model.addAttribute("nameproduct", nameproduct);
        model.addAttribute("total",total);
        model.addAttribute("idcart",idcart);


        return "user/checkout";
    }
    @PostMapping
    public String oder(@ModelAttribute Oder oder,Model model,@RequestParam("idcart") Long idcart){
        Cart cart=cartRepo.getById(idcart);
        int quantity= cart.getQuantity();
        Long idproduct= cart.getIdproduct();
        String nameproduct= cart.getNameprodcut();
        String username= cart.getUsername();
        String fullname= oder.getFullname();
        String email=oder.getEmail();
        String add= oder.getAddress();
        String city= oder.getCity();
        String phone=oder.getPhone();
        Date datee = new Date();
        String ngayMua = new SimpleDateFormat("yyyy/MM/dd").format(datee.getTime());
        Oder oder1= new Oder(username,fullname,email,phone,add,city,idproduct,quantity,ngayMua,nameproduct);
        oderRepo.save(oder1);
        System.err.println(idcart);



        return "redirect:/user?d= Dat hang thanh cong";
    }

}
