package com.example.demotda.service.impl;

import com.example.demotda.model.Supplier;
import com.example.demotda.repositorie.SupplierRepo;
import com.example.demotda.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SupplierServiceImpl implements SupplierService {
    private SupplierRepo supplierRepo;
    @Autowired
    public SupplierServiceImpl(SupplierRepo supplierRepo){
        this.supplierRepo=supplierRepo;
    }
    @Override
    public List<Supplier> listAll(){
        return supplierRepo.findAll();
    }
    @Override
    public void saveSupplier(Supplier supplier){
        supplierRepo.save(supplier);
    }
}
