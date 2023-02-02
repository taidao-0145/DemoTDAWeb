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
    private int quantity;
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "importMasterId")
    private ImportMaster importMaster;


    public ImportProduct(Date dateadd, int quantity, Product product, ImportMaster importMaster) {

        this.quantity = quantity;
        this.product = product;
        this.importMaster = importMaster;
    }
}
