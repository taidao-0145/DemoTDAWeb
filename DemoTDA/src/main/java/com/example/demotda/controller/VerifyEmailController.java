package com.example.demotda.controller;

import com.example.demotda.model.User;
import com.example.demotda.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkmail")
public class VerifyEmailController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String mail(){
        return "checkmail";
    }
    @PostMapping
    public String verify(HttpSession session, @RequestParam("code") String codeuser, Model model){
        String codeverify= (String) session.getAttribute("code");
        User user= (User) session.getAttribute("user");
        if(codeuser.equals(codeverify)){
            userRepo.save(user);
            model.addAttribute("messdone","Đăng kí tài khoản thành công");
            return "login";
        }
        else{
            model.addAttribute("messcheck","Nhập sai mã");
            return "checkmail";
        }

    }

}
