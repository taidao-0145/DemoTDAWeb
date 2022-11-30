package com.example.demotda.controller;

import com.example.demotda.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sendmail")
public class SendMail {
    @Autowired
    MailConfig mailConfig;

    @GetMapping
    public String send(){
        return "sendmail";
    }
    @PostMapping
    public String mail(@RequestParam("email") String email,
                       @RequestParam("sj") String sj,
                       @RequestParam("mess") String mess, Model model){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("daotai23092001@gmail.com");
        msg.setTo(email);
        msg.setSubject(sj);
        msg.setText(mess);
        mailConfig.get().send(msg);


        return "login";
    }
}
