package com.example.demotda.service;

import com.example.demotda.model.ReportProduct;

import java.util.List;

public interface ReportProductService {

    void save(ReportProduct reportProduct);

    List<ReportProduct> listAll();
    void deleteById(Long id);
}
