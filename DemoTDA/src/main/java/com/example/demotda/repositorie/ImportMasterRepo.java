package com.example.demotda.repositorie;

import com.example.demotda.model.ImportMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportMasterRepo extends JpaRepository<ImportMaster,Long> {
}
