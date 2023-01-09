package com.example.demotda.service.impl;

import com.example.demotda.model.ExportMaster;
import com.example.demotda.repositorie.ExportMasterRepo;
import com.example.demotda.service.ExportMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportMasterServiceImpl implements ExportMasterService {
    @Autowired
    private ExportMasterRepo exportMasterRepo;

    @Override
    public List<ExportMaster> listAll() {
        return exportMasterRepo.findAll();
    }

    @Override
    public void save(ExportMaster exportMaster) {
        exportMasterRepo.save(exportMaster);
    }
}
