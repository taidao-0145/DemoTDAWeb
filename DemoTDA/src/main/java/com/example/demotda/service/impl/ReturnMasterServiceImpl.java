package com.example.demotda.service.impl;

import com.example.demotda.model.ReturnProductMaster;
import com.example.demotda.repositorie.ReturnMasterRepo;
import com.example.demotda.service.ReturnMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnMasterServiceImpl implements ReturnMasterService {
    private  ReturnMasterRepo returnMasterRepo;
    @Autowired
    public ReturnMasterServiceImpl(ReturnMasterRepo returnMasterRepo) {
        this.returnMasterRepo = returnMasterRepo;
    }

    @Override
    public void save(ReturnProductMaster returnProductMaster) {
        returnMasterRepo.save(returnProductMaster);
    }
}
