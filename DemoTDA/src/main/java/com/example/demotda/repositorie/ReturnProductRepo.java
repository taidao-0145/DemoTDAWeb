package com.example.demotda.repositorie;


import com.example.demotda.model.ReturnProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ReturnProductRepo extends JpaRepository<ReturnProduct,Long> {
    @Transactional
    @Query(value="select sum(quantity) FROM demotda.return_product where product_id=?", nativeQuery=true)
    Long countReturn(Long id);
}
