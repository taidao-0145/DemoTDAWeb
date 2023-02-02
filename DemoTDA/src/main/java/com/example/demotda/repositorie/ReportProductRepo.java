package com.example.demotda.repositorie;

import com.example.demotda.model.ReportProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportProductRepo extends JpaRepository<ReportProduct,Long> {
}
