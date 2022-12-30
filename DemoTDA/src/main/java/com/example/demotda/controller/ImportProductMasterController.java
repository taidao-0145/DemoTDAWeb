package com.example.demotda.controller;

import com.example.demotda.model.ImportMaster;
import com.example.demotda.model.Supplier;
import com.example.demotda.service.ImportMasterService;
import com.example.demotda.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ImportProductMasterController {
    private  ImportMasterService importMasterService;
    private SupplierService supplierService;
    @Autowired
    public ImportProductMasterController(ImportMasterService importMasterService,SupplierService supplierService) {
        this.importMasterService = importMasterService;
        this.supplierService=supplierService;
    }

    @GetMapping("/formImportMaster")
    public String formImportMaster(Model model){
        List<Supplier> suppliers=supplierService.listAll();
        model.addAttribute("suppliers",suppliers);
        return "admin/importmaster";
    }

    @PostMapping("/addImportMaster")
    public String importMaster(@ModelAttribute Supplier supplier){
        Date date= new Date();
        ImportMaster importMaster= new ImportMaster();
        importMaster.setDateadd(date);
        importMaster.setSupplier(supplier);
        importMasterService.save(importMaster);
        System.err.println(supplier.getId());
        return "redirect:/formImportProduct";
    }

}
