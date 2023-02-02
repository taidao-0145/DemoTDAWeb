package com.example.demotda.repositorie;

import com.example.demotda.model.ExportProduct;
import com.example.demotda.model.ImportProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ExportProductRepo extends JpaRepository<ExportProduct,Long> {

    @Transactional
    @Query(value="SELECT * FROM demotda.export_product where  export_master_id=?", nativeQuery=true)
    List<ExportProduct> findByMaster(Long id);

    @Transactional
    @Query(value="select sum(quantity) FROM demotda.export_product where product_id=?", nativeQuery=true)
    Long countExport(Long id);
}
