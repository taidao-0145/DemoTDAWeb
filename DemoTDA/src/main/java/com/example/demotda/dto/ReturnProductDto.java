package com.example.demotda.dto;


import com.example.demotda.model.ImportProduct;
import com.example.demotda.model.ReturnProduct;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReturnProductDto {
    private List<ReturnProduct> returnProducts;

    public ReturnProductDto(){
        this.returnProducts=new ArrayList<>();
    }
    public ReturnProductDto(List<ReturnProduct> returnProducts) {
        this.returnProducts = returnProducts;
    }

    public void addReturnProduct(ReturnProduct returnProduct){
        this.returnProducts.add(returnProduct);
    }
}
