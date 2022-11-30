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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private Long idproduct;
    private String nameprodcut;
    private String img;
    private int price;
    private int quantity;

    private int total;

    public Cart(String username, Long idproduct, String nameprodcut, String img, int price, int quantity, int total) {
        this.username = username;
        this.idproduct = idproduct;
        this.nameprodcut = nameprodcut;
        this.img = img;
        this.price = price;
        this.quantity = quantity;
        this.total= total;
    }
}
