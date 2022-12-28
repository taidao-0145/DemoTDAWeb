package com.example.demotda.service;


import com.example.demotda.dto.ProductDto;
import com.example.demotda.model.ImportProduct;
import org.springframework.data.domain.Page;

public interface ImportProductService {
    void save(ProductDto productDto);
    Page<ImportProduct> listAll(int pageNumber);
}
