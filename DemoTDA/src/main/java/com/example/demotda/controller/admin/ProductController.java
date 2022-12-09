package com.example.demotda.controller.admin;

import com.example.demotda.model.Product;
import com.example.demotda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/productadmin")
    public String productad(Model model){
        return listbyPage(model,1);
    }
    @GetMapping("/page")
    public String listbyPage(Model model,@RequestParam("page") int currentPage){

        Page<Product> page= productService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Product> listall= page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listall", listall);
        return "admin/product";
    }

}
