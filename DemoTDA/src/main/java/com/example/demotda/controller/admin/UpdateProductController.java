package com.example.demotda.controller.admin;

import com.example.demotda.model.Category;
import com.example.demotda.model.Product;
import com.example.demotda.model.Supplier;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.repositorie.SupplierRepo;
import com.example.demotda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/updateproduct")
public class UpdateProductController {
    private ProductRepo productRepo;

    private SupplierRepo supplierRepo;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    public UpdateProductController(ProductRepo productRepo,
                                   SupplierRepo supplierRepo){
        this.productRepo=productRepo;
        this.supplierRepo= supplierRepo;
    }

    @GetMapping
    public String viewupdate(@RequestParam("id") Long id, Model model){
        Product product=  productRepo.getById(id);
        model.addAttribute("product", product);
        List<Category> listtype= categoryService.getListAll();
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
