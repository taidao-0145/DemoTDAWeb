package com.example.demotda.repositorie;

import com.example.demotda.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface ProductRepo extends  JpaRepository<Product,Long> {


    @Query(value="SELECT * FROM product order by id DESC limit 0,8", nativeQuery=true)
    List<Product> listNew();

    @Query(value="SELECT * FROM product order by sale DESC limit 0,8", nativeQuery=true)
    List<Product> listSale();

    @Transactional
    @Modifying
    @Query(value="UPDATE product set soluong=soluong-? where  id=?", nativeQuery=true)
    void exportProduct(int quantity,Long id);

    @Transactional
    @Modifying
    @Query(value="SELECT * FROM product WHERE soluong>0 and soluong <10 order by soluong ASC limit 0,5", nativeQuery=true)
    List<Product> checkAmount();

    @Transactional
    @Modifying
    @Query(value="SELECT * FROM product WHERE soluong=0 ", nativeQuery=true)
    List<Product> outOfStock();

    @Transactional
    @Modifying
    @Query(value="select * FROM demotda.product where datediff(now(), dateadd)>60", nativeQuery=true)
    List<Product> checkInventory();


    @Transactional
    @Modifying
    @Query(value="select * from demotda.product where nameproduct like %?%", nativeQuery=true)
    List<Product> searchProduct(String keyword);

    @Transactional
    @Modifying
    @Query(value="UPDATE product set soluong=soluong+?, cansell=cansell+? where  id=?", nativeQuery=true)
    void updateImport(int quantity,int canSell,Long id);

    @Transactional
    @Modifying
    @Query(value="UPDATE product set soluong=soluong-? where  id=?", nativeQuery=true)
    void updateExport(int quantity,Long id);

    @Transactional
    @Modifying
    @Query(value="UPDATE product set  cansell=cansell-? where  id=?", nativeQuery=true)
    void reportProduct(int quantity,Long id);
}
