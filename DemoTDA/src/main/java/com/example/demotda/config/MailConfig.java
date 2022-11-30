package com.example.demotda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender get(){
        JavaMailSenderImpl mail= new JavaMailSenderImpl();
        mail.setHost("smtp.gmail.com");

        mail.setPort(587);
        mail.setUsername("daotai23092001@gmail.com");
        mail.setPassword("bhjaawbgljwrnylb");

        Properties mp= new Properties();
        mp.put("mail.smtp.auth",true);
        mp.put("mail.smtp.starttls.enable",true);
        mp.put("mail.transport.protocol","smtp");
        mp.put("mail.smtp.ssl.protocols","TLSv1.2");
        mp.put("mail.debug","true");
        mail.setJavaMailProperties(mp);

        return mail;
    }

}
