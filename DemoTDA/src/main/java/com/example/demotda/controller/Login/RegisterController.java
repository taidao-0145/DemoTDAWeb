package com.example.demotda.controller.Login;

import com.example.demotda.config.MailConfig;
import com.example.demotda.model.User;
import com.example.demotda.repositorie.UserRepo;
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
    private UserRepo userRepo;
    @GetMapping
    public String reg(){
        return "login/register";
    }
    @PostMapping
    public String signup(@ModelAttribute User user,
                         @RequestParam("repass") String repass, Model model,
                         RedirectAttributes ra, HttpSession session){
        int test= 0;
        String email_pattern = "^[a-zA-Z0-9][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        String pass_pattern="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
        User checkUser= userRepo.findUserByUsername(user.getUsername());
        if(checkUser != null){
            model.addAttribute("messusername","Tên đăng nhập đã tồn tại");
            test=1;
        }
        if(!repass.equals(user.getPass())){
            model.addAttribute("messrepass","Nhập lại mật khẩu chưa chính xác");
            test=1;
        }
        if(Pattern.matches(email_pattern,user.getEmail())==false){
            model.addAttribute("messmail","Nhập sai định dạng Email");
            test=1;
        }
        if(Pattern.matches(pass_pattern,user.getPass())==false){
            model.addAttribute("messpass","Mật khẩu chưa đủ mạnh(> 8 ký tự,hoa,thường,kí tự...");
            test=1;
        }
        if(test==1){
            model.addAttribute("username",user.getUsername());
            model.addAttribute("email",user.getEmail());
            model.addAttribute("phone",user.getPhone());
            model.addAttribute("pass",user.getPass());
            return "login/register";
        }

        else {
            user.setRole("USER");
            user.setPass(new BCryptPasswordEncoder().encode(user.getPass()));
            session.setAttribute("user",user);
            int code= (int) (Math.random()*100000);
            String code1= Integer.toString(code);
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("daotai23092001@gmail.com");
            msg.setTo(user.getEmail());
            msg.setSubject("Xác thực đăng kí cho:"+ user.getEmail());
            msg.setText("Mã xác thực là: "+ code1);
            mailConfig.get().send(msg);
            session.setAttribute("code",code1);
            return "login/checkmail";
        }

    }
}
