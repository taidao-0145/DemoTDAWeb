package com.example.demotda.repositorie;


import com.example.demotda.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Long> {

    @Transactional
    @Modifying
    @Query(value="UPDATE demotda.supplier set debt=debt+? where  id=?", nativeQuery=true)
    void updateDebtSupplier(long quantity,Long id);

    @Transactional
    @Modifying
    @Query(value="UPDATE demotda.supplier set debt=debt-? where  id=?", nativeQuery=true)
    void updateDebtSupplierContinuePayment(long quantity,Long id);
}
