package com.example.demotda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JsonBackReference
    private UserProfile userProfile;


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

    public User(Long id, String username, String email, int phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
}
