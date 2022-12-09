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
    private Long idproduct;
    private String nameproduct;
    private String typeproduct;
    private int quantity;
    private String supplier;
    private int total;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date exportdate;

    public ProductSold(Long idproduct, String nameproduct, String typeproduct, int quantity, String supplier, int total, Date exportdate) {
        this.idproduct = idproduct;
        this.nameproduct = nameproduct;
        this.typeproduct = typeproduct;
        this.quantity = quantity;
        this.supplier = supplier;
        this.total = total;
        this.exportdate = exportdate;
    }
}
