package com.example.demotda.service;

import com.example.demotda.model.Product;
import com.example.demotda.repositorie.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> listProduct(){
        return productRepo.findAll();
    }
}
