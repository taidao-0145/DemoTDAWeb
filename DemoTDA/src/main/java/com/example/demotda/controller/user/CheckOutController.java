package com.example.demotda.controller.user;

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

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public String viewcheckout(
                               @RequestParam("idproduct") Long id,
                               @RequestParam("total") int total,
                               @RequestParam("idcart") Long idcart,
                               Model model){
        Product p= productRepo.getById(id);
        Cart cart= cartRepo.getById(idcart);
        int quantity= cart.getQuantity();
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
        String img= cart.getImg();
        int total= cart.getTotal();

        Product product= productRepo.getById(idproduct);
        int soluong=product.getSoluong();
        System.err.println(soluong);

        List<Oder> listoderbyid= oderRepo.getOderByIdproduct(idproduct);
        int quantityoder=0;
        for( Oder oderr: listoderbyid){
            quantityoder+=oderr.getQuantity();
        }
        if((soluong-quantityoder)<2){

            return "redirect:/viewcart?d=san pham da het hang";
        }
        else {
            Oder oder1= new Oder(username,fullname,email,phone,add,city,idproduct,quantity,new Date(),nameproduct,img,total,1);
            oderRepo.save(oder1);
            cartRepo.deleteById(idcart);
            return "redirect:/user?d= Dat hang thanh cong";
        }

    }

}
