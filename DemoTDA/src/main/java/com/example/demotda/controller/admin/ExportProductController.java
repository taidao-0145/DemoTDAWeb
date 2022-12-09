package com.example.demotda.controller.admin;

import com.example.demotda.model.Oder;
import com.example.demotda.model.Product;
import com.example.demotda.model.ProductSold;
import com.example.demotda.repositorie.OderRepo;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.repositorie.ProductSoldRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/exportproduct")
public class ExportProductController {
    private OderRepo oderRepo;
    private ProductRepo productRepo;
    private ProductSoldRepo productSoldRepo;
    @Autowired
    public ExportProductController(OderRepo oderRepo,
                                   ProductRepo productRepo,
                                   ProductSoldRepo productSoldRepo){
        this.oderRepo=oderRepo;
        this.productRepo=productRepo;
        this.productSoldRepo=productSoldRepo;
    }

    @GetMapping
    public String exportproduct(@RequestParam("idoder") Long idoder,
                                @RequestParam("idproduct") Long idproduct){
        Oder oder= oderRepo.getById(idoder);
        int quantity= oder.getQuantity();
        int total= oder.getTotal();
        Product product=productRepo.getById(idproduct);
        String typeproduct= product.getTypeproduct();
        String nameproduct= product.getNameproduct();
        String supplier= product.getHangsp();
       ProductSold productSold= new ProductSold(idproduct,nameproduct,
               typeproduct,quantity,supplier,total,new Date());
       productSoldRepo.save(productSold);
       productRepo.exportproduct(quantity,idproduct);
       oderRepo.deleteById(idoder);
        return "redirect:/productsold";
    }
}
