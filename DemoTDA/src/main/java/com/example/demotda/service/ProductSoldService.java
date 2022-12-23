package com.example.demotda.service;

import com.example.demotda.model.ProductSold;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductSoldService {
    Page<ProductSold> listAll(int pageNumber);
    void save(ProductSold productSold);
    List<ProductSold> listSoldDay(String today);
    List<ProductSold> listSoldWeek();
    long revenueYesterday(String today1, String today2);
    List<ProductSold> findAll();
    List<ProductSold> searchDateProductSold(String startDate, String endDate);
    List<ProductSold> TopSelling();
}
