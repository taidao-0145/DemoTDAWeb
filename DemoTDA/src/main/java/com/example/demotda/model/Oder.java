package com.example.demotda.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String city;
    private Long idproduct;
    private int quantity;
    private String date;
    private String nameproduct;

    public Oder(String username, String fullname, String email, String phone, String address, String city, Long idproduct, int quantity, String date,String nameproduct) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.idproduct = idproduct;
        this.quantity = quantity;
        this.date = date;
        this.nameproduct=nameproduct;
    }
}
