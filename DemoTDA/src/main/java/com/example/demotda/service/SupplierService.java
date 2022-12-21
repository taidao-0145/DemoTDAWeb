package com.example.demotda.service;

import com.example.demotda.model.Supplier;

import java.util.List;


public interface SupplierService {
    List<Supplier> listAll();
    void saveSupplier(Supplier supplier);
}
