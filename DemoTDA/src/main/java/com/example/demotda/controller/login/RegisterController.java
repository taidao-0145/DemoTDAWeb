package com.example.demotda.controller.login;

import com.example.demotda.dto.UserDto;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class RegisterController {
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
