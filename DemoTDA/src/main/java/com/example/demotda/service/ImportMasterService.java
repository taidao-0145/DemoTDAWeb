package com.example.demotda.service;

import com.example.demotda.model.ImportMaster;

import java.util.List;

public interface ImportMasterService {
    void save(ImportMaster importMaster);
    List<ImportMaster> listAll();
    void paymentImport(Long money ,Long id);
    ImportMaster findById(Long id);

    List<ImportMaster> findBySupp(Long id);
    List<ImportMaster> findByUser(Long id);
}
