package com.example.demotda.service.impl;

import com.example.demotda.model.ImportMaster;
import com.example.demotda.repositorie.ImportMasterRepo;
import com.example.demotda.service.ImportMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportMasterServiceImpl implements ImportMasterService {
    @Autowired
    private ImportMasterRepo importMasterRepo;
    @Override
    public void save(ImportMaster importMaster) {
        importMasterRepo.save(importMaster);
    }
}
