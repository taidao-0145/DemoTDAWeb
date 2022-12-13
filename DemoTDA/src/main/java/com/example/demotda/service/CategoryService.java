package com.example.demotda.service;

import com.example.demotda.model.Category;
import com.example.demotda.repositorie.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getListAll(){
        return categoryRepo.findAll();
    }
    public void save(Category category){
        categoryRepo.save(category);
    }
}
