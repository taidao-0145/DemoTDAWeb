package com.example.demotda.repositorie;

import com.example.demotda.model.Revenue;
import com.example.demotda.model.TopSellingg;
import com.example.demotda.model.ProductSold;
import com.example.demotda.model.TopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductSoldRepo extends JpaRepository<ProductSold,Long> {


    @Transactional
    @Modifying
    @Query(value="SELECT * FROM demotda.product_sold WHERE export_date > date_sub(now(), interval 1 week);", nativeQuery=true)
    List<ProductSold> listSoldWeek();

    @Transactional
    @Modifying
    @Query(value="SELECT * FROM demotda.product_sold WHERE export_date > date_sub(now(), interval 1 month);", nativeQuery=true)
    List<ProductSold> listSoldMonth();

    @Transactional
    @Modifying
    @Query(value="SELECT * FROM demotda.product_sold where export_date between ? and now()", nativeQuery=true)
    List<ProductSold> listSoldDay(String today);

    @Transactional
    @Query(value="SELECT sum(total) FROM demotda.product_sold where export_date between date_sub(?, interval 1 day) and ?;", nativeQuery=true)
    Long revenueYesterday(String today1, String today2);

    @Transactional
    @Modifying
    @Query(value="SELECT * FROM demotda.product_sold where export_date between ? and ?", nativeQuery=true)
    List<ProductSold> searchDateProductSold(String startDate, String endDate);

    @Query("SELECT c FROM ProductSold c WHERE (:startDate is null or c.exportDate >= :startDate) and (:endDate is null"
            + " or c.exportDate <= :endDate)")
    List<ProductSold> findDateProductSold(@Param("startDate") Date startDate, @Param("endDate") Date endDate);



    @Query(value="select new com.example.demotda.model.TopSellingg(idProduct,nameProduct,sum(quantity)) FROM ProductSold group by idProduct,nameProduct order by sum(quantity) desc")
    List<TopSellingg> topSelling();

    @Query(value="select new com.example.demotda.model.TopUser(idUser,sum(total),sum(quantity)) FROM ProductSold group by idUser order by sum(total) desc")
    List<TopUser> topUser();

    @Query(value="select new com.example.demotda.model.Revenue(DATE(exportDate),sum(quantity),sum(total)) FROM ProductSold group by DATE(exportDate) order by DATE(exportDate) asc")
    List<Revenue> revenue();

    @Transactional
    @Modifying
    @Query(value="SELECT * FROM demotda.product_sold where id_user=?", nativeQuery=true)
    List<ProductSold> boughtProduct(Long id);

    @Transactional
    @Query(value="SELECT sum(total) from demotda.product_sold", nativeQuery=true)
    long totalSold();

    @Transactional
    @Query(value="select sum(quantity) FROM demotda.product_sold where id_product=?", nativeQuery=true)
    Long countSold(Long id);


}
