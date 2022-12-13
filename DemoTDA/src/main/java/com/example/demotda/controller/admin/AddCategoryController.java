package com.example.demotda.controller.admin;

import com.example.demotda.model.Category;
import com.example.demotda.repositorie.CategoryRepo;
import com.example.demotda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/addCategory")
public class AddCategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String viewAddCategory(){

        return "admin/addcategory";
    }
    @PostMapping
    public String addCategory(@RequestParam("category") String category){
        Category c= new Category(category);
        categoryService.save(c);
        return "redirect:/addproduct";
    }
}
