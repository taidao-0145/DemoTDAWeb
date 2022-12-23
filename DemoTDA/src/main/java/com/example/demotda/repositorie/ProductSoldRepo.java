package com.example.demotda.repositorie;

import com.example.demotda.model.Product;
import com.example.demotda.model.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductSoldRepo extends JpaRepository<ProductSold,Long> {


//    @Transactional
//    @Modifying
//    @Query(value="SELECT * FROM demotda.product_sold WHERE export_date > date_sub(now(), interval 1 day);", nativeQuery=true)
//    List<ProductSold> listSoldDay();

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
    long revenueYesterday(String today1, String today2);

    @Transactional
    @Modifying
    @Query(value="SELECT * FROM demotda.product_sold where export_date between ? and ?", nativeQuery=true)
    List<ProductSold> searchDateProductSold(String startDate, String endDate);

    @Transactional
    @Query(value="select id_product,name_product,sum(quantity) FROM product_sold group by id_product,name_product order by sum(quantity) desc", nativeQuery=true)
    List<ProductSold> TopSelling();

}
