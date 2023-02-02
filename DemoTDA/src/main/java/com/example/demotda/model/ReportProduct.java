package com.example.demotda.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
public class ReportProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private Date date;
    private String status;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    public ReportProduct(int quantity, Date date, String status, String note, Product product) {
        this.quantity = quantity;
        this.date = date;
        this.status = status;
        this.note = note;
        this.product = product;
    }
}
