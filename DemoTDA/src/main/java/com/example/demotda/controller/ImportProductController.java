package com.example.demotda.controller;

import com.example.demotda.model.ImportProduct;
import com.example.demotda.model.Product;
import com.example.demotda.model.Supplier;
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

import java.util.List;

@Controller
public class ImportProductController {
    private  ImportProductService importProductService;
    private ProductService productService;
    private SupplierService supplierService;
    @Autowired
    public ImportProductController(ImportProductService importProductService,
                                   ProductService productService,
                                   SupplierService supplierService) {
        this.importProductService = importProductService;
        this.productService=productService;
        this.supplierService=supplierService;
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

    @GetMapping("/formImportProduct")
    public String formImportProduct(Model model){
        List<Product> products= productService.listProduct();
        model.addAttribute("products",products);
//        List<Supplier> suppliers=supplierService.listAll();
//        model.addAttribute("suppliers",suppliers);
        return "admin/formimportproduct";
    }

    @PostMapping("/addImportProduct")
    public String addImportProduct(@RequestParam("supplier") String supplier,
                                   @RequestParam("soluong") int soluong,@RequestParam("price") int price){
        System.err.println(supplier);
        System.err.println(soluong);
        System.err.println(price);

        return "redirect:/formImportProduct";
    }



}
