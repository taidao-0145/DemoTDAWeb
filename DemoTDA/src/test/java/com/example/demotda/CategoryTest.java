package com.example.demotda;

import com.example.demotda.model.Category;
import com.example.demotda.repositorie.CategoryRepo;
import com.example.demotda.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryTest {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void testCreateCategory(){
        Category category= new Category(1,"TDA");
        categoryRepo.save(category);
    }
}
