package com.example.demotda.controller.Login;

import com.example.demotda.config.MailConfig;
import com.example.demotda.model.User;
import com.example.demotda.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/forgotpassword")
public class ForgotPassController {
    private UserRepo userRepo;
    private MailConfig mailConfig;

    @Autowired
    public ForgotPassController(UserRepo userRepo, MailConfig mailConfig){
        this.mailConfig=mailConfig;
        this.userRepo=userRepo;
    }

    @GetMapping
    public String viewforgot(){
        return "login/forgotpass";
    }
    @PostMapping
    public String forgot(@RequestParam("username") String username,
                         @RequestParam("email") String email, Model model, HttpSession session){
        User user= userRepo.findByUsernameAndEmail(username,email);
        if(user==null){
            model.addAttribute("mess", "Tài khoản không tồn tại");
            return "login/forgotpass";
        }
        else {
            int codemail= (int) (Math.random()*1000000);
            String code2= Integer.toString(codemail);
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("daotai23092001@gmail.com");
            msg.setTo(email);
            msg.setSubject("Xác thực đổi mật khẩu cho:"+email);
            msg.setText("Mã xác thực là: "+ code2);
            mailConfig.get().send(msg);
            session.setAttribute("codess",code2);
            session.setAttribute("usernameforgot", username);
            session.setAttribute("emailforgot", email);
            return "login/mailforgotpass";
        }


    }
}
