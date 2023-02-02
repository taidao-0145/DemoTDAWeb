package com.example.demotda.service.impl;

import com.example.demotda.model.ReportProduct;
import com.example.demotda.repositorie.ReportProductRepo;
import com.example.demotda.service.ReportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportProductServiceImpl implements ReportProductService {
    @Autowired
    private ReportProductRepo reportProductRepo;
    @Override
    public void save(ReportProduct reportProduct) {
        reportProductRepo.save(reportProduct);
    }

    @Override
    public List<ReportProduct> listAll() {
        return reportProductRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        reportProductRepo.deleteById(id);
    }
}
