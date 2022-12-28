package com.example.demotda.controller.login;

import com.example.demotda.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/changepassword")
public class ChangePassword {
    private UserRepo userRepo;
    @Autowired
    public ChangePassword(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    @PostMapping
    public String changePass(@RequestParam("pass") String pass, Model model, HttpSession session){
        String passPattern="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
        if(!Pattern.matches(passPattern,pass)){
            model.addAttribute("mess","Mật khẩu chưa đủ mạnh(> 8 ký tự,hoa,thường,kí tự...");
            return "login/changepassword";
        }
        else {
            String username= (String) session.getAttribute("usernameforgot");
            String passSenCode= new BCryptPasswordEncoder().encode(pass);
            userRepo.changepassword(passSenCode,username);
        }
        return "login/login";
    }
}
