package com.example.demotda.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameproduct;
    private String typeproduct;
    private int soluong;
    private String hangsp;
    private String img;
    private int price;
    private int sale;
    private String nhacungcap;
}
