package com.example.demotda.service.impl;

import com.example.demotda.model.ReturnProduct;
import com.example.demotda.repositorie.ReturnProductRepo;
import com.example.demotda.service.ReturnProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnProductServiceImpl implements ReturnProductService {
    private  ReturnProductRepo returnProductRepo;
    @Autowired
    public ReturnProductServiceImpl(ReturnProductRepo returnProductRepo) {
        this.returnProductRepo = returnProductRepo;
    }

    @Override
    public void saveAll(List<ReturnProduct> returnProducts) {
        returnProductRepo.saveAll(returnProducts);
    }

    @Override
    public Long countReturn(Long id) {
        return returnProductRepo.countReturn(id);
    }
}
