package com.example.demotda.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private int phone;
    private String pass;
    private String img;

    private String role;

    public User(String username, String email, int phone, String pass, String img) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
        this.img = img;
    }

    public User(String username, String pass, List<SimpleGrantedAuthority> list) {
        this.username = username;
        this.pass=pass;
    }
}
