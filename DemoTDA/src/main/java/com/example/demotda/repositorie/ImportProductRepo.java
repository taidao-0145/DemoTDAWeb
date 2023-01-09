package com.example.demotda.repositorie;

import com.example.demotda.model.ImportMaster;
import com.example.demotda.model.ImportProduct;

import com.example.demotda.model.TopSellingg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ImportProductRepo extends JpaRepository<ImportProduct,Long> {
    @Transactional
    @Query(value="SELECT * FROM demotda.import_product where  import_master_id=?", nativeQuery=true)
    List<ImportProduct> findByMaster(Long id);

    @Transactional
    @Query(value="select sum(quantity) FROM demotda.import_product where product_id=?", nativeQuery=true)
    Long countImport(Long id);

}
