package com.example.demotda.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mailforgot")
public class VerifyEmailForgot {

    @PostMapping
    public String confirm(@RequestParam("code") String code, HttpSession session, Model model){
        String codess= (String) session.getAttribute("codess");
        if(code.equals(codess)){
            return "login/changepassword";
        }
        else {
            model.addAttribute("mess", "Nhập sai mã");
            return "login/mailforgotpass";
        }

    }
}
