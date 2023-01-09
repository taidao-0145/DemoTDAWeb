package com.example.demotda.service.impl;

import com.example.demotda.model.ExportProduct;
import com.example.demotda.model.ImportProduct;
import com.example.demotda.repositorie.ExportProductRepo;
import com.example.demotda.repositorie.ImportProductRepo;
import com.example.demotda.service.ExportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportProductServiceImpl implements ExportProductService {
    private ExportProductRepo exportProductRepo;
    @Autowired
    public ExportProductServiceImpl(ExportProductRepo exportProductRepo) {

        this.exportProductRepo = exportProductRepo;
    }

    @Override
    public Page<ExportProduct> listAll(int pageNumber) {
        Pageable pageable= PageRequest.of(pageNumber-1,5);
        return exportProductRepo.findAll(pageable);
    }

    @Override
    public void saveAll(List<ExportProduct> exportProducts) {
        exportProductRepo.saveAll(exportProducts);
    }

    @Override
    public List<ExportProduct> findByMaster(Long id) {
        return exportProductRepo.findByMaster(id);
    }

    @Override
    public Long countExport(Long id) {
        return exportProductRepo.countExport(id);
    }

}
