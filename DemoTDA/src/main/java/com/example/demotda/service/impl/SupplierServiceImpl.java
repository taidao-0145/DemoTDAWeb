package com.example.demotda.service.impl;

import com.example.demotda.model.Supplier;
import com.example.demotda.repositorie.SupplierRepo;
import com.example.demotda.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Supplier> listAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, 5);
        return  supplierRepo.findAll(pageable);
    }

    @Override
    public List<Supplier> listAll(){
        return supplierRepo.findAll();
    }
    @Override
    public void saveSupplier(Supplier supplier){
        supplierRepo.save(supplier);
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepo.findById(id).orElse(null);
    }

    @Override
    public void updateDebtSupplier(long quantity, Long id) {
        supplierRepo.updateDebtSupplier(quantity,id);
    }

    @Override
    public void updateDebtSupplierContinuePayment(long quantity, Long id) {
        supplierRepo.updateDebtSupplierContinuePayment(quantity,id);
    }
}
