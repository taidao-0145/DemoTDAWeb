package com.example.demotda.repositorie;


import com.example.demotda.model.ReturnProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnMasterRepo extends JpaRepository<ReturnProductMaster,Long> {
}
