package com.example.demotda.repositorie;

import com.example.demotda.model.ImportMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ImportMasterRepo extends JpaRepository<ImportMaster,Long> {

    @Transactional
    @Modifying
    @Query(value="UPDATE demotda.import_master set debt=debt-? where  id=?", nativeQuery=true)
    void paymentImport(Long money ,Long id);

    @Transactional
    @Query(value="SELECT * FROM demotda.import_master where  supplier_id=?", nativeQuery=true)
    List<ImportMaster> findBySupp(Long id);

    @Transactional
    @Query(value="SELECT * FROM demotda.import_master where  user_id=?", nativeQuery=true)
    List<ImportMaster> findByUser(Long id);

}
