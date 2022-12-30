package com.example.demotda.controller;

import com.example.demotda.dto.ProductDto;
import com.example.demotda.model.Category;
import com.example.demotda.model.Product;
import com.example.demotda.model.Supplier;
import com.example.demotda.service.CategoryService;
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

public class ProductController {
    private  ProductService productService;
    private CategoryService categoryService;
    private SupplierService supplierService;

    @Autowired
    public ProductController(ProductService productService,CategoryService categoryService,
                             SupplierService supplierService) {
        this.categoryService=categoryService;
        this.productService = productService;
        this.supplierService=supplierService;
    }
    @GetMapping("/productAdmin")
    public String productAdmin(Model model){
        return listByPage(model,1);
    }
    @GetMapping("/page")
    public String listByPage(Model model,@RequestParam("page") int currentPage){
        Page<Product> page= productService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Product> listAllProduct= page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listAllProduct", listAllProduct);
        return "admin/product";
    }
    @GetMapping("/deleteProduct")
    public String viewDelete(@RequestParam("id") Long id, Model model){
        Product product= productService.getProduct(id);
        model.addAttribute("product", product);
        return "admin/deleteproduct";
    }

    @GetMapping("/confirmDelete")
    public String confirmDelete(@RequestParam("id") Long id){
        productService.delete(id);
        return "redirect:/productAdmin";
    }

    @GetMapping("/addProduct")
    public String viewAddProduct(Model model){
        List<Category> categories= categoryService.getListAll();
        model.addAttribute("categories", categories);
        List<Supplier> suppliers= supplierService.listAll();
        model.addAttribute("suppliers", suppliers);
        return "admin/addproduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductDto productDto,@RequestParam("new") String string){
        productService.save(productDto);
        System.err.println(string);
        return "redirect:/productAdmin";
    }
    @GetMapping("/updateProduct")
    public String viewUpdate(@RequestParam("id") Long id, Model model){
        Product product=  productService.getProduct(id);
        model.addAttribute("product", product);
        List<Category> categories= categoryService.getListAll();
        model.addAttribute("categories", categories);
        List<Supplier> suppliers= supplierService.listAll();
        model.addAttribute("suppliers", suppliers);
        return "admin/updateproduct";
    }
    @PostMapping("/updateProduct")
    public String update(@ModelAttribute ProductDto productDto){
        productService.updateProduct(productDto);
        return "redirect:/productAdmin";
    }
    @GetMapping("/viewProduct")
    public String viewProduct(@RequestParam("id") Long id, Model model){
        Product product= productService.getProduct(id);
        model.addAttribute("product",product);
        return "user/viewproduct";
    }
    @GetMapping("/store")
    public String viewStoreUser(Model model){
        return listByPageStore(model,1);
    }
    @GetMapping("/pageStore")
    public String listByPageStore(Model model, @RequestParam("page") int currentPage){
        Page<Product> pageStore= productService.listStoreAll(currentPage);
        long totalItems= pageStore.getTotalElements();
        int totalPages= pageStore.getTotalPages();
        List<Product> listAll= pageStore.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listAll", listAll);
        return "user/store";
    }
    @PostMapping("/searchProduct")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model){
        List<Product> listAllProduct= productService.searchProduct(keyword);
        model.addAttribute("listAllProduct",listAllProduct);
        return "admin/product";
    }

}
