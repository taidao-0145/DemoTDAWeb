package com.example.demotda.service;

import com.example.demotda.model.Revenue;
import com.example.demotda.model.TopSellingg;
import com.example.demotda.model.ProductSold;
import com.example.demotda.model.TopUser;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductSoldService {
    Page<ProductSold> listAll(int pageNumber);
    void save(ProductSold productSold);
    List<ProductSold> listSoldDay(String today);
    List<ProductSold> listSoldWeek();
    Long revenueYesterday(String today1, String today2);
    List<ProductSold> findAll();
    List<ProductSold> searchDateProductSold(String startDate, String endDate);
    List<TopSellingg> topSelling();
    List<TopUser> topUser();
    List<ProductSold> boughtProduct(Long id);
    List<Revenue> revenue();
    long totalSold();
    Long countSold(Long id);
    List<ProductSold> findDateProductSold(Date startDate, Date endDate);


}
