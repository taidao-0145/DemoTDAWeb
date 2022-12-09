package com.example.demotda.controller.admin;

import com.example.demotda.model.Product;
import com.example.demotda.model.ProductSold;
import com.example.demotda.repositorie.ProductSoldRepo;
import com.example.demotda.service.ProductService;
import com.example.demotda.service.ProductSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class ProductSoldController {
    @Autowired
    private ProductSoldService productSoldService;

    @GetMapping("/productsold")
    public String viewsold(Model model){
        return listbyPage(model,1);
    }
    @GetMapping("/pagesold")
    public String listbyPage(Model model,@RequestParam("page") int currentPage){

        Page<ProductSold> page= productSoldService.listAll(currentPage);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<ProductSold> listall= page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listSold", listall);
        return "admin/productsold";
    }

}
