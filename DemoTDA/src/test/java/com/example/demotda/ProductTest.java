package com.example.demotda;

import com.example.demotda.model.Category;
import com.example.demotda.model.Product;
import com.example.demotda.model.Supplier;
import com.example.demotda.repositorie.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
public class ProductTest {
    @Autowired
    private ProductRepo repo;

    @Test
    public void testCreateProduct(){
        Product product= new Product("name",3,"img",4,5,new Date(),new Category(),new Supplier());
        repo.save(product);
    }
}
