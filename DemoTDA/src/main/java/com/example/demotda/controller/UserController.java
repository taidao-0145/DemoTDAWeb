package com.example.demotda.controller;

import com.example.demotda.model.Product;
import com.example.demotda.model.User;
import com.example.demotda.model.UserProfile;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.repositorie.UserRepo;
import com.example.demotda.service.ProductService;
import com.example.demotda.service.UserProfileService;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private ProductService productService;
    private UserService userService;
    private UserProfileService userProfileService;
    @Autowired
    public UserController(ProductService productService,UserService userService,UserProfileService userProfileService){
        this.userService= userService;
        this.productService=productService;
        this.userProfileService=userProfileService;
    }
    @GetMapping("/user")
    public String ViewUser(Model model,Principal principal){
        if(principal==null){
            return "login/login";
        }
        User user = userService.findUserByUsername( principal.getName());
        List<Product> listNew= productService.listNew();
        List<Product> listSale= productService.listSale();
        model.addAttribute("listSale",listSale);
        model.addAttribute("listNew",listNew);
        model.addAttribute("username",user.getUsername());
        System.err.println(principal.getName());
        return "user/user";
    }
    @ModelAttribute("username")
    public void username(Model model, Principal principal, HttpSession session){
        String user= principal.getName();
        session.setAttribute("username", user);
    }

    @GetMapping("/userProfile")
    public String viewUser(Principal principal, Model model){
        String username= principal.getName();
        User user= userService.findUserByUsername(username);
        UserProfile userProfile= userProfileService.findUserProfileByUser(user);
        model.addAttribute("userprofile",userProfile);
        return "user/userprofile";
    }
    @PostMapping("/userProfile")
    public String updateProfile(@ModelAttribute UserProfile userProfile,
                                @RequestParam("username") String username,
                                @RequestParam("email") String email,
                                @RequestParam("phone") int phone){
        User user = userService.findUserByUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        userProfile.setUser(user);
        user.setUserProfile(userProfile);
        userService.save(user);
        return "redirect:/userProfile";
    }

    @GetMapping("/userManagement")
    public String ViewUser(Model model){
        List<User> listUser= userService.findAll();
        model.addAttribute("listUser",listUser);
        return "admin/userManagement";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long idUser){
        userService.deleteUser(idUser);
        return "redirect:/userManagement";
    }

    @GetMapping("/profileUserManagement")
    public String Profile(@RequestParam("id") Long idUser, Model model){
        User user= userService.findUserById(idUser);
        UserProfile userProfile=userProfileService.findUserProfileByUser(user);
        model.addAttribute("userProfile",userProfile);
        return "admin/profileUserManagement";
    }

    @PostMapping("/searchUser")
    public String SearchUser(Model model, @RequestParam("name") String name){
        List<User> listSearch= userService.Search(name);
        model.addAttribute("listUser",listSearch);
        return "admin/userManagement";
    }
}
