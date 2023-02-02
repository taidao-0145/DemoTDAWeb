package com.example.demotda.service;

import com.example.demotda.model.ExportProduct;
import com.example.demotda.model.ImportProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExportProductService {
    Page<ExportProduct> listAll(int pageNumber);
    void saveAll(List<ExportProduct> exportProducts);
    List<ExportProduct> findByMaster(Long id);
    Long countExport(Long id);

}
