package com.example.demotda.controller.login;

import com.example.demotda.model.User;
import com.example.demotda.model.UserProfile;
import com.example.demotda.repositorie.UserRepo;
import com.example.demotda.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkmail")
public class VerifyEmailController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String mail(){
        return "login/checkmail";
    }
    @PostMapping
    public String verify(HttpSession session, @RequestParam("code") String codeUser, Model model){
        String codeVerify= (String) session.getAttribute("code");
        User user= (User) session.getAttribute("user");
        if(codeUser.equals(codeVerify)){
            UserProfile userProfile= new UserProfile("update","update","update","update","update");
            user.setUserProfile(userProfile);
            userProfile.setUser(user);
            userRepo.save(user);
            model.addAttribute("messdone","Đăng kí tài khoản thành công");
            return "login/login";
        }
        else{
            model.addAttribute("messcheck","Nhập sai mã");
            return "login/checkmail";
        }

    }

}
