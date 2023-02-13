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
    private int cansell;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    @OneToMany(mappedBy = "product",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<ImportProduct> importProducts;

    @OneToMany(mappedBy = "product",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<ReturnProduct> returnProducts;

    @OneToMany(mappedBy = "product",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<ReportProduct> reportProducts;

    @OneToMany(mappedBy = "product",cascade =CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;


    public Product(String nameproduct, int soluong, String img, int price, int sale, Date dateadd, Category category, Supplier supplier) {
        this.nameproduct = nameproduct;
        this.soluong = soluong;
        this.img = img;
        this.price = price;
        this.sale = sale;
        this.dateadd = dateadd;
        this.category = category;
        this.supplier = supplier;
    }

    public Product(Long id, String nameproduct, int soluong, String img, int price, int sale, Date dateadd, Category category, Supplier supplier) {
        this.id = id;
        this.nameproduct = nameproduct;
        this.soluong = soluong;
        this.img = img;
        this.price = price;
        this.sale = sale;
        this.dateadd = dateadd;
        this.category = category;
        this.supplier = supplier;

    }
}
