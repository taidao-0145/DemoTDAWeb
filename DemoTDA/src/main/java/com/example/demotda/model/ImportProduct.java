package com.example.demotda.model;



import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@RequiredArgsConstructor
@Entity
public class ImportProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateadd;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    public ImportProduct(Date dateadd, int quantity, Product product) {
        this.dateadd = dateadd;
        this.quantity = quantity;
        this.product = product;
    }
}
