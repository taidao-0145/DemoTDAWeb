package com.example.demotda.service;

import com.example.demotda.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getListAll();
    void save(Category category);
    Category findByCategoryByName(String name);
}
