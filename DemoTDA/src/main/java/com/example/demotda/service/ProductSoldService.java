package com.example.demotda.service;

import com.example.demotda.model.Product;
import com.example.demotda.model.ProductSold;
import com.example.demotda.repositorie.ProductSoldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductSoldService {
    @Autowired
    private ProductSoldRepo productSoldRepo;

    public Page<ProductSold> listAll(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1, 5);
        return  productSoldRepo.findAll(pageable);
    }
}
