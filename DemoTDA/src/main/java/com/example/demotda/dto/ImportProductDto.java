package com.example.demotda.dto;

import com.example.demotda.model.ImportProduct;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImportProductDto {

    private List<ImportProduct> importProducts;

    public ImportProductDto(){
        this.importProducts=new ArrayList<>();
    }
    public ImportProductDto(List<ImportProduct> importProducts) {
        this.importProducts = importProducts;
    }

    public void addImportProduct(ImportProduct importProduct){
        this.importProducts.add(importProduct);
    }
}
