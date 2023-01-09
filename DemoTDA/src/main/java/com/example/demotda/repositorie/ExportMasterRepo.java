package com.example.demotda.repositorie;

import com.example.demotda.model.ExportMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportMasterRepo extends JpaRepository<ExportMaster,Long> {
}
