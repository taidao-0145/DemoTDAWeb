package com.example.demotda.controller.login;


import com.example.demotda.model.User;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller

public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String viewLogin(Model model,Principal principal){
        if(principal != null){
            return "redirect:/user";
        }

        return "login/login";
    }

    @GetMapping("/checkRole")
    public String checkRole(Principal principal){
        String userName= principal.getName();
        User user= userService.findUserByUsername(userName);
        String role= user.getRole();
        if(role.equals("ROLE_ADMIN") || role.equals("ROLE_MANAGE") || role.equals("ROLE_STAFF")){
            return "redirect:/admin/index";
        }
        else {
            return "redirect:/user";
        }
    }

}
