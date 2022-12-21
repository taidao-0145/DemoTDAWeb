package com.example.demotda.controller;

import com.example.demotda.model.Category;
import com.example.demotda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/addCategory")
    public String ViewAddCategory(){
        return "admin/add-category";
    }
    @PostMapping("/addCategory")
    public String AddCategory(@RequestParam("category") String category){
        Category c= new Category(category);
        categoryService.save(c);
        return "redirect:/addProduct";
    }
}
