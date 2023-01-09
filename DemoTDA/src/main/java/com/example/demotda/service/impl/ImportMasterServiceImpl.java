package com.example.demotda.service.impl;

import com.example.demotda.model.ImportMaster;
import com.example.demotda.repositorie.ImportMasterRepo;
import com.example.demotda.service.ImportMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportMasterServiceImpl implements ImportMasterService {
    @Autowired
    private ImportMasterRepo importMasterRepo;
    @Override
    public void save(ImportMaster importMaster) {
        importMasterRepo.save(importMaster);
    }

    @Override
    public List<ImportMaster> listAll() {
        return importMasterRepo.findAll();
    }

    @Override
    public void paymentImport(Long money, Long id) {
        importMasterRepo.paymentImport(money,id);
    }

    @Override
    public ImportMaster findById(Long id) {
        return importMasterRepo.findById(id).orElse(null);
    }

    @Override
    public List<ImportMaster> findBySupp(Long id) {
        return importMasterRepo.findBySupp(id);
    }
}
