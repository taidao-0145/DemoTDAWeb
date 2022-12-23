package com.example.demotda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String nameproduct;
    private int soluong;
    private String img;
    private int price;
    private int sale;
    private Date dateadd;
    private Long categoryId;
    private Long supplierId;
}
