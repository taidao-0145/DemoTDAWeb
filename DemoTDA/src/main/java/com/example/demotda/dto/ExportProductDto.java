package com.example.demotda.dto;

import com.example.demotda.model.ExportProduct;
import com.example.demotda.model.ImportProduct;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ExportProductDto {
    private List<ExportProduct> exportProducts;

    public ExportProductDto(List<ExportProduct> exportProducts) {
        this.exportProducts = exportProducts;
    }
    public ExportProductDto(){
        this.exportProducts=new ArrayList<>();
    }
    public void addExportProduct(ExportProduct exportProduct){

        this.exportProducts.add(exportProduct);
    }
}
