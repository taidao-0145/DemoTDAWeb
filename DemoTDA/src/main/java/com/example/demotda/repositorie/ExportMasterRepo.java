package com.example.demotda.repositorie;

import com.example.demotda.model.ExportMaster;

import com.example.demotda.model.ImportMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ExportMasterRepo extends JpaRepository<ExportMaster,Long> {
    @Transactional
    @Query(value="SELECT * FROM demotda.export_master where  user_id=?", nativeQuery=true)
    List<ExportMaster> findByUser(Long id);
}
