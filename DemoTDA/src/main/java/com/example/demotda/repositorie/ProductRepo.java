package com.example.demotda.repositorie;

import com.example.demotda.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface ProductRepo extends  JpaRepository<Product,Long> {

    @Query(value="SELECT * FROM product order by id DESC limit 0,6", nativeQuery=true)
    List<Product> listnew();

    @Query(value="SELECT * FROM product order by sale DESC limit 0,6", nativeQuery=true)
    List<Product> listsale();

    @Transactional
    @Modifying
    @Query(value="UPDATE product set soluong=soluong-? where  id=?", nativeQuery=true)
    void exportproduct(int quantity,Long id);

    @Transactional
    @Modifying
    @Query(value="SELECT count(*) FROM product WHERE soluong <5;", nativeQuery=true)
    void checkcountquantity();

    @Transactional
    @Modifying
    @Query(value="select id product ORDER BY 1 DESC limit 1", nativeQuery=true)
    Integer checkid();

}
