package com.example.demotda.repositorie;


import com.example.demotda.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProductRepo extends JpaRepository<TypeProduct,Integer> {

}
