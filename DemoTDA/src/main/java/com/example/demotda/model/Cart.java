package com.example.demotda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


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

    private int quantity;

    private int status;

//    @OneToMany(mappedBy = "cart",cascade =CascadeType.ALL)
//    @JsonManagedReference
//    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private Set<Product> products;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "cart")
    @JsonBackReference
    private Oder oder;


}
