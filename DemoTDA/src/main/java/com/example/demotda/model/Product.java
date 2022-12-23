package com.example.demotda.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameproduct;
    private int soluong;
    private String img;
    private int price;
    private int sale;
    private Date dateadd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cartId")
//    private Cart cart;


    public Product(String nameproduct, int soluong, String img, int price, int sale, Date dateadd) {
        this.nameproduct = nameproduct;
        this.soluong = soluong;
        this.img = img;
        this.price = price;
        this.sale = sale;
        this.dateadd = dateadd;
    }

    public Product(String nameproduct, int soluong, String img, int price, int sale, Date dateadd, Category category) {
        this.nameproduct = nameproduct;
        this.soluong = soluong;
        this.img = img;
        this.price = price;
        this.sale = sale;
        this.dateadd = dateadd;
        this.category = category;
    }
}
