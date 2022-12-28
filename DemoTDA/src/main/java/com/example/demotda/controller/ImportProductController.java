package com.example.demotda.controller;

import com.example.demotda.model.ImportProduct;
import com.example.demotda.service.ImportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ImportProductController {
    private  ImportProductService importProductService;
    @Autowired
    public ImportProductController(ImportProductService importProductService) {
        this.importProductService = importProductService;
    }

    @GetMapping("/importProduct")
    public String viewImportProduct(Model model){
        return listByPage(model,1);
    }

    @GetMapping("/pageImportProduct")
    public String listByPage(Model model, @RequestParam("page") int currentPage){
        Page<ImportProduct> page= importProductService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<ImportProduct> listAll= page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listImport", listAll);
        return "admin/importproduct";
    }
}
