package com.example.demotda.dto;


import lombok.*;


@Getter
@Setter
public class ProductSoldDto {
    private Long idProduct;
    private String nameProduct;
    private Long sumQuantity;
}
