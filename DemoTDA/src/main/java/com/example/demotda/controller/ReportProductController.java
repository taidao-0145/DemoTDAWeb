package com.example.demotda.controller;

import com.example.demotda.model.Product;
import com.example.demotda.model.ReportProduct;
import com.example.demotda.service.ProductService;
import com.example.demotda.service.ReportProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ReportProductController {
    @Autowired
    private ReportProductService reportProductService;

    @Autowired
    private ProductService productService;

    @GetMapping("/reportProduct")
    public String formReportProduct(@RequestParam("id") Long idProduct, Model model){
        Product product= productService.getProduct(idProduct);
        model.addAttribute("product",product);
        return "admin/formreport";
    }

    @PostMapping("/addReport")
    public String reportReport(@RequestParam("status") String status,@RequestParam("quantity") int quantity,
                               @RequestParam("id") Long idProduct,@RequestParam("note") String note){
        Product product= productService.getProduct(idProduct);
        ReportProduct reportProduct= new ReportProduct(quantity,new Date(),status,note,product);
        reportProductService.save(reportProduct);
        productService.reportProduct(quantity,idProduct);
        return "redirect:/failProduct";
    }

    @GetMapping("/failProduct")
    public String viewFailProduct(Model model){
        List<ReportProduct> listFail=reportProductService.listAll();
        model.addAttribute("listFail",listFail);
        return "admin/failproduct";
    }
    @GetMapping("/cancelProductFail")
    public String cancelProductFail(@RequestParam("id") Long id,@RequestParam("idProduct") Long idProduct,
                                    @RequestParam("quantity") int quantity){
        reportProductService.deleteById(id);
        productService.updateExport(quantity, idProduct);
        return "redirect:/failProduct";
    }

}
