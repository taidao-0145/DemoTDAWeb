package com.example.demotda.service;

import com.example.demotda.model.Product;
import com.example.demotda.model.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;


public interface SupplierService {

    Page<Supplier> listAll(int pageNumber);
    List<Supplier> listAll();
    void saveSupplier(Supplier supplier);
    Supplier findById(Long id);

    void updateDebtSupplier(long quantity,Long id);

    void updateDebtSupplierContinuePayment(long quantity,Long id);
}
