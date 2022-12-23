package com.example.demotda.controller;

import com.example.demotda.model.Cart;
import com.example.demotda.model.Oder;
import com.example.demotda.model.Product;
import com.example.demotda.model.User;
import com.example.demotda.service.CartService;
import com.example.demotda.service.OderService;
import com.example.demotda.service.ProductService;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {
    private ProductService productService;
    private CartService cartService;
    private UserService userService;
    private OderService oderService;
    @Autowired
    public CartController(ProductService productService,
                          CartService cartService, UserService userService,
                          OderService oderService){
        this.userService=userService;
        this.cartService=cartService;
        this.productService=productService;
        this.oderService=oderService;
    }
    @GetMapping("/addToCart")
    public String AddToCart(@RequestParam("id") Long id, Principal principal){
        String username=principal.getName();
        User user= userService.findUserByUsername(username);
        Product product= productService.getProduct(id);
        int total= product.getSoluong();
        Cart cart= new Cart();
        cart.setQuantity(1);
        cart.setStatus(1);
        cart.setProduct(product);
        cart.setUser(user);
        if(total>0){
            if(cartService.getCartByUserAndProductAndStatus(user,product,1)== null){
                cartService.saveCart(cart);
                return "redirect:/viewCart?e=them vao gio hang thanh cong";
            }
            else{
                cartService.updateCheck(user.getId(),product.getId());
                return "redirect:/viewCart?e=update gio hang thanh cong";
            }
        }
        else {
            return "redirect:/user?err= san pham da het hang";
        }
    }
    @GetMapping("/viewCart")
    public String viewCart(Principal principal, Model model){
        String username= principal.getName();
        User user= userService.findUserByUsername(username);
        List<Cart> listCart=cartService.getCartByUserAndStatus(user,1);
        model.addAttribute("listCart", listCart);
        model.addAttribute("username",username);
        return "user/view-cart";
    }

    @GetMapping("/deleteCart")
    public String deleteCart(@RequestParam("id") Long id){
        cartService.DeleteCart(id);
        return "redirect:/viewCart";
    }

    @GetMapping("/checkout")
    public String viewCheckout(@RequestParam("idCart") Long idCart, Model model){
        User user= cartService.findCardById(idCart).getUser();
        model.addAttribute("user", user);
        Cart cart= cartService.findCardById(idCart);
        model.addAttribute("cart", cart);
        return "user/checkout";
    }

    @PostMapping("/checkout")
    public String oder(@RequestParam("cartId") Long cartId, @RequestParam("address") String address){
        Cart cart=cartService.findCardById(cartId);
        int quantity= cart.getQuantity();
        int price= cart.getProduct().getPrice();
        int total= quantity*price;
        Product product= cart.getProduct();
        int soluong=product.getSoluong();
        int countOder= cartService.countOderById(product.getId());
        if((soluong-countOder)<2){
            return "redirect:/viewCart?d=san pham da het hang";
        }
        else {
            Oder oder= new Oder();
            oder.setCart(cart);
            oder.setTotal(total);
            oder.setDate(new Date());
            oder.setIdstatus(1);
            oder.setAddress(address);
            oder.setUsername(cart.getUser().getUsername());
            oderService.saveOder(oder);
            cartService.updateStatusCart(cartId);
            return "redirect:/oder?oder= Dat hang thanh cong";
        }
    }
    @GetMapping("/numCart")
    public String NumCart(@RequestParam("id") Long id,@RequestParam("check") String check,@RequestParam("num") int num){
        if(check.equals("plus")){
            cartService.updatePlusQuantity(id);
        }
        else{
            if(num<2){
                return "redirect:/viewCart";
            }
            else {
                cartService.updateMinusQuantity(id);
            }
        }
        return "redirect:/viewCart";
    }
}
