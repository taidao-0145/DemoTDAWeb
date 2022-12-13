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

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/addProduct")
public class AddProductController {
    private ProductRepo productRepo;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SupplierRepo supplierRepo;


    @Autowired
    public AddProductController(ProductRepo productRepo,
                                 SupplierRepo supplierRepo){
        this.productRepo=productRepo;
        this.supplierRepo=supplierRepo;
    }

    @GetMapping
    public String viewAddProduct(Model model){
        List<Category> categories= categoryService.getListAll();
        model.addAttribute("categories", categories);
        List<Supplier> listsup= supplierRepo.findAll();
        model.addAttribute("listsup", listsup);
        model.addAttribute("date", new Date());
        return "admin/addproduct";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product, @RequestParam("category") String category){
        String nameProduct= product.getNameproduct();
        String typeproduct= product.getTypeproduct();
        Category category1= new Category();
        int quantity= product.getSoluong();
        String hangSp= product.getHangsp();
        String img= product.getImg();
        int price= product.getPrice();
        int sale= product.getSale();
        Date date= new Date();
        Product p= new Product(nameProduct,typeproduct,quantity,hangSp,img,price,sale,date);
        productRepo.save(p);

        return "redirect:/productadmin";
    }
}
