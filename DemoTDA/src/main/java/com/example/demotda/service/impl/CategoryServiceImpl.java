package com.example.demotda.service.impl;

import com.example.demotda.model.Category;
import com.example.demotda.repositorie.CategoryRepo;
import com.example.demotda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;
    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo){
        this.categoryRepo=categoryRepo;
    }
    @Override
    public List<Category> getListAll() {
        return categoryRepo.findAll();
    }
    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }
    @Override
    public Category findByCategoryByName(String name) {
        return categoryRepo.getCategoryByName(name);
    }

}
