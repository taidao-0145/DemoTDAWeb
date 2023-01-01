package com.example.demotda.service.impl;


import com.example.demotda.dto.ProductDto;
import com.example.demotda.model.ImportProduct;
import com.example.demotda.repositorie.ImportProductRepo;
import com.example.demotda.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImportProductImpl implements ImportProductService {
    private  ImportProductRepo importProductRepo;
    @Autowired
    public ImportProductImpl(ImportProductRepo importProductRepo) {
        this.importProductRepo = importProductRepo;
    }

    @Override
    public void save(ProductDto productDto) {
    }

    @Override
    public Page<ImportProduct> listAll(int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber-1,5);
        return importProductRepo.findAll(pageable);
    }

    @Override
    public void saveAll(List<ImportProduct> importProducts) {
        importProductRepo.saveAll(importProducts);
    }
}
