package com.example.demotda.service;

import com.example.demotda.model.ExportMaster;
import java.util.List;

public interface ExportMasterService {
    List<ExportMaster> listAll();

    void save(ExportMaster exportMaster);
}
