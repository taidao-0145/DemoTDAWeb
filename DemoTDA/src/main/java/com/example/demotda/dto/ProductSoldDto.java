package com.example.demotda.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSoldDto {
    private Long id;
    private Long idProduct;
    private Long idUser;
    private String nameProduct;
    private String category;
    private int quantity;
    private String supplier;
    private int total;
    private Date exportDate;
    private int sum;
}
