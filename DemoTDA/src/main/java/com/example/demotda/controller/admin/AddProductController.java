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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/addproduct")
public class AddProductController {
    private ProductRepo productRepo;
    private TypeProductRepo typeProductRepo;
    private SupplierRepo supplierRepo;

    @Autowired
    public AddProductController(ProductRepo productRepo,TypeProductRepo typeProductRepo,
                                 SupplierRepo supplierRepo){
        this.productRepo=productRepo;
        this.typeProductRepo=typeProductRepo;
        this.supplierRepo=supplierRepo;
    }

    @GetMapping
    public String addpro(Model model){
        List<TypeProduct> listtype= typeProductRepo.findAll();
        model.addAttribute("listtype", listtype);
        List<Supplier> listsup= supplierRepo.findAll();
        model.addAttribute("listsup", listsup);
        return "admin/addproduct";
    }

    @PostMapping
    public String addproduct(@ModelAttribute Product product){
        productRepo.save(product);

        return "redirect:/productadmin";
    }
}
