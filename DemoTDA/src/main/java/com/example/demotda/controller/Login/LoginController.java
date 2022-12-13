package com.example.demotda.controller.Login;

import com.example.demotda.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private UserRepo userRepo;
    @Autowired
    public LoginController(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    @GetMapping
    public String viewlogin(Model model){
//        model.addAttribute("mess",mess);

        return "login/login";
    }
//    @PostMapping
//    public String login(@RequestParam("username") String username,
//                        @RequestParam("pass") String pass,
//                        Model model){
//        if(userRepo.findUserByUsernameAndPass(username,pass)==null){
//            model.addAttribute("mess", "Sai thông tin đăng nhập");
//            return "login";
//        }
//        else {
//            return "index";
//        }
//
//
//    }
}
