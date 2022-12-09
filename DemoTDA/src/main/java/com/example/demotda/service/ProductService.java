package com.example.demotda.service;

import com.example.demotda.model.Product;
import com.example.demotda.repositorie.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> listProduct(){

        return productRepo.findAll();
    }
    public Page<Product> listAll(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1, 3);
        return  productRepo.findAll(pageable);
    }
}
