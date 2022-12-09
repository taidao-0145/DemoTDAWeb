package com.example.demotda.controller.admin;

import com.example.demotda.model.Product;
import com.example.demotda.model.Supplier;
import com.example.demotda.model.TypeProduct;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.repositorie.SupplierRepo;
import com.example.demotda.repositorie.TypeProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/updateproduct")
public class UpdateProductController {
    private ProductRepo productRepo;
    private TypeProductRepo typeProductRepo;
    private SupplierRepo supplierRepo;


    @Autowired
    public UpdateProductController(ProductRepo productRepo, TypeProductRepo typeProductRepo,
                                   SupplierRepo supplierRepo){
        this.productRepo=productRepo;
        this.typeProductRepo=typeProductRepo;
        this.supplierRepo= supplierRepo;
    }

    @GetMapping
    public String viewupdate(@RequestParam("id") Long id, Model model){
        Product product=  productRepo.getById(id);
        model.addAttribute("product", product);
        List<TypeProduct> listtype= typeProductRepo.findAll();
        model.addAttribute("listtype", listtype);
        List<Supplier> listsup= supplierRepo.findAll();
        model.addAttribute("listsup", listsup);
        return "admin/updateproduct";
    }
    @PostMapping
    public String update(@ModelAttribute Product product){
        productRepo.save(product);
        return "redirect:/productadmin";

    }
}
