package com.example.demotda.service;

import com.example.demotda.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    List<Category> getListAll();
    void save(Category category);
    Category findByCategoryByName(String name);
}
