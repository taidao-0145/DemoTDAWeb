package com.example.demotda.controller;

import com.example.demotda.dto.UserProfileDto;
import com.example.demotda.model.*;
import com.example.demotda.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private ProductService productService;
    private UserService userService;
    private UserProfileService userProfileService;
    private ProductSoldService productSoldService;
    private ImportMasterService importMasterService;
    private ExportMasterService exportMasterService;
    @Autowired
    public UserController(ProductService productService,UserService userService,
                          UserProfileService userProfileService,ProductSoldService productSoldService,
                          ImportMasterService importMasterService,ExportMasterService exportMasterService){
        this.userService= userService;
        this.productService=productService;
        this.userProfileService=userProfileService;
        this.productSoldService=productSoldService;
        this.importMasterService=importMasterService;
        this.exportMasterService=exportMasterService;
    }
    @GetMapping("/user")
    public String viewUser(Model model,Principal principal){
        if(principal==null){
            return "login/login";
        }
        User user = userService.findUserByUsername( principal.getName());
        List<Product> listNew= productService.listNew();
        List<Product> listSale= productService.listSale();
        model.addAttribute("listSale",listSale);
        model.addAttribute("listNew",listNew);
        model.addAttribute("username",user.getUsername());
        return "user/user";
    }
    @ModelAttribute("username")
    public void username(Model model, Principal principal, HttpSession session){
        String user= principal.getName();
        session.setAttribute("username", user);
    }

    @GetMapping("/userProfile")
    public String viewUserProfile(Principal principal, Model model){
        String username= principal.getName();
        User user= userService.findUserByUsername(username);
        UserProfile userProfile= userProfileService.findUserProfileByUser(user);
        model.addAttribute("userprofile",userProfile);
        List<ProductSold> boughtProduct=productSoldService.boughtProduct(user.getId());
        model.addAttribute("boughtProduct",boughtProduct);
        return "user/userprofile";
    }
    @PostMapping("/userProfile")
    public String updateProfile(@ModelAttribute UserProfileDto userProfileDto,
                                @RequestParam("username") String username,
                                @RequestParam("email") String email,
                                @RequestParam("phone") int phone){
        userService.save(userProfileDto,username,email,phone);
        return "redirect:/userProfile";
    }

    @GetMapping("/userManagement")
    public String viewUser(Model model){
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
    public String profileUser(@RequestParam("id") Long idUser, Model model){
        User user= userService.findUserById(idUser);
        UserProfile userProfile=userProfileService.findUserProfileByUser(user);
        model.addAttribute("userProfile",userProfile);
        List<ImportMaster> listImport= importMasterService.findByUser(idUser);
        model.addAttribute("listImport",listImport);
        List<ExportMaster> listExport= exportMasterService.findByUser(idUser);
        model.addAttribute("listExport",listExport);
        return "admin/profileUserManagement";
    }

    @PostMapping("/searchUser")
    public String searchUser(Model model, @RequestParam("name") String name){
        List<User> listSearch= userService.search(name);
        model.addAttribute("listUser",listSearch);
        return "admin/userManagement";
    }

    @GetMapping("/repurchase")
    public String repurchase(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        Product product= productService.getProduct(id);
        if(product==null){
            return "redirect:/userProfile?d=no product";
        }
        else {
            redirectAttributes.addAttribute("id",id);
            return "redirect:/viewProduct";
        }
    }
    @GetMapping("/help")
    public String help(){
        return "admin/help";
    }
}
