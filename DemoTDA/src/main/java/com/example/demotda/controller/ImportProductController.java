package com.example.demotda.controller;

import com.example.demotda.dto.ImportProductDto;
import com.example.demotda.model.ImportMaster;
import com.example.demotda.model.ImportProduct;
import com.example.demotda.model.Product;
import com.example.demotda.service.ImportMasterService;
import com.example.demotda.service.ImportProductService;
import com.example.demotda.service.ProductService;
import com.example.demotda.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ImportProductController {
    private  ImportProductService importProductService;
    private ProductService productService;
    private ImportMasterService importMasterService;
    @Autowired
    public ImportProductController(ImportProductService importProductService,
                                   ProductService productService,
                                   ImportMasterService importMasterService) {
        this.importProductService = importProductService;
        this.productService=productService;
        this.importMasterService=importMasterService;
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

    @PostMapping("/formImportProduct")
    public String formImportProduct(Model model, @RequestParam("num") int num){
        List<Product> products= productService.listProduct();
        model.addAttribute("products",products);
        ImportProductDto formImport= new ImportProductDto();
        for (int i = 1; i <= num; i++) {
            formImport.addImportProduct(new ImportProduct());
        }
        model.addAttribute("formImport",formImport);
        return "admin/formimportproduct";
    }

    @PostMapping("/addImportProduct")
    public String addImportProduct(@ModelAttribute ImportProductDto formImport,@RequestParam("supplier") String supplier){
        List<ImportProduct> list= formImport.getImportProducts();
        long total=0;
        for (ImportProduct importPro : list) {
            total+=importPro.getPrice();
        }
        ImportMaster importMaster= new ImportMaster(new Date(),supplier,total);
        importMasterService.save(importMaster);

        for (ImportProduct importPro : list) {
            importPro.setImportMaster(importMaster);
        }
        importProductService.saveAll(list);
        for (ImportProduct importPro : list) {
            productService.updateImport(importPro.getQuantity(),importPro.getProduct().getId());
        }
        return "redirect:/importProduct";
    }

}
