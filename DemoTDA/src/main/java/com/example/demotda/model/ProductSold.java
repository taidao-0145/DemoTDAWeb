package com.example.demotda.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class ProductSold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idProduct;
    private String nameProduct;
    private String category;
    private int quantity;
    private String supplier;
    private int total;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date exportDate;

    public ProductSold(Long idProduct, String nameProduct, String category, int quantity, String supplier, int total, Date exportDate) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.category = category;
        this.quantity = quantity;
        this.supplier = supplier;
        this.total = total;
        this.exportDate = exportDate;
    }
}
