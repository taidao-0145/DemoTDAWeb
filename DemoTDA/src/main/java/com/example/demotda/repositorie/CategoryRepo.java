package com.example.demotda.repositorie;

import com.example.demotda.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

    Category getCategoryByName(String name);
}
