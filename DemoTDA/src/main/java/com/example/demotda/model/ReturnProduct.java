package com.example.demotda.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
public class ReturnProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "returnMasterId")
    private ReturnProductMaster returnProductMaster;

    public ReturnProduct(int quantity, Product product, ReturnProductMaster returnProductMaster) {
        this.quantity = quantity;
        this.product = product;
        this.returnProductMaster = returnProductMaster;
    }
}
