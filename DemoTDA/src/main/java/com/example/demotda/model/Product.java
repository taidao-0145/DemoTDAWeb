package com.example.demotda.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    private Date dateadd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;


    public Product(String nameproduct, String typeproduct, int soluong, String hangsp, String img, int price, int sale, Date dateadd) {
        this.nameproduct = nameproduct;
        this.typeproduct = typeproduct;
        this.soluong = soluong;
        this.hangsp = hangsp;
        this.img = img;
        this.price = price;
        this.sale = sale;
        this.dateadd = dateadd;
    }

    public Product(String nameproduct, String typeproduct, int soluong, String hangsp, String img, int price, int sale, Date dateadd, Category category) {
        this.nameproduct = nameproduct;
        this.typeproduct = typeproduct;
        this.soluong = soluong;
        this.hangsp = hangsp;
        this.img = img;
        this.price = price;
        this.sale = sale;
        this.dateadd = dateadd;
        this.category = category;
    }
}
