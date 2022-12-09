package com.example.demotda.repositorie;

import com.example.demotda.model.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSoldRepo extends JpaRepository<ProductSold,Long> {
}
