package com.example.demotda.controller.login;

import com.example.demotda.config.MailConfig;
import com.example.demotda.dto.UserDto;
import com.example.demotda.model.User;
import com.example.demotda.repositorie.UserRepo;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private UserService userService;
    @GetMapping
    public String reg(){
        return "login/register";
    }
    @PostMapping
    public String signup(@ModelAttribute UserDto userDto,
                         @RequestParam("repass") String repass, Model model,HttpSession session){

           return userService.signUp(userDto,repass,model,session);
    }
}
