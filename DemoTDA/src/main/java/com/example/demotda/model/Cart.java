package com.example.demotda.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private Set<Product> products;


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
