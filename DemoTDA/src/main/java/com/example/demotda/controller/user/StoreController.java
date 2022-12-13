package com.example.demotda.controller.user;

import com.example.demotda.model.Product;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class StoreController {
    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;
    @Autowired
    public StoreController(ProductRepo productRepo){
        this.productRepo=productRepo;
    }
    @GetMapping("/store")
    public String viewStore(Model model){

        return listByPage(model,1);
    }

    @GetMapping("/pageStore")
    public String listByPage(Model model, @RequestParam("page") int currentPage){

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
}
