package com.example.demotda.controller;

import com.example.demotda.model.ImportMaster;
import com.example.demotda.model.Product;
import com.example.demotda.model.Supplier;
import com.example.demotda.service.ImportMasterService;
import com.example.demotda.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SupplierController {
    private SupplierService supplierService;

    private ImportMasterService importMasterService;
    @Autowired
    public SupplierController(SupplierService supplierService,ImportMasterService importMasterService){
        this.importMasterService=importMasterService;
        this.supplierService=supplierService;
    }

    @GetMapping("/addSupplier")
    public String viewAdd(){
        return "admin/addsupplier";
    }

    @PostMapping("/addSupplier")
    public String addSupplier(@RequestParam("supplier") String sup, @RequestParam("address") String add){
        Supplier s= new Supplier(sup,add);
        supplierService.saveSupplier(s);
        return "redirect:/addProduct";

    }

    @GetMapping("/supplierManagement")
    public String supplierManagement(Model model){
        return listByPage(model,1);
    }


    @GetMapping("/pageSupplier")
    public String listByPage(Model model,@RequestParam("page") int currentPage){
        Page<Supplier> page= supplierService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Supplier> listSupp= page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listSupp", listSupp);
        return "admin/suppliermanagement";
    }

    @GetMapping("/detailSupplier")
    public String detailSupplier(@RequestParam("id") Long idSupplier,Model model){
        List<ImportMaster> listMaster= importMasterService.findBySupp(idSupplier);
        model.addAttribute("listMaster",listMaster);
        Supplier supplier= supplierService.findById(idSupplier);
        model.addAttribute("supplier",supplier);
        return "admin/detailsupplier";
    }
}
