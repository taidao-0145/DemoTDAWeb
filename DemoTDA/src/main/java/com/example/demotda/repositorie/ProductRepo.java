package com.example.demotda.repositorie;

import com.example.demotda.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends  JpaRepository<Product,Long> {

    @Query(value="SELECT * FROM product order by id DESC limit 0,6", nativeQuery=true)
    List<Product> listnew();

    @Query(value="SELECT * FROM product order by sale DESC limit 0,6", nativeQuery=true)
    List<Product> listsale();


}
