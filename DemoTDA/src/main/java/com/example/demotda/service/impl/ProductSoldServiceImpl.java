package com.example.demotda.service.impl;

import com.example.demotda.model.Revenue;
import com.example.demotda.model.TopSellingg;
import com.example.demotda.model.ProductSold;
import com.example.demotda.model.TopUser;
import com.example.demotda.repositorie.ProductSoldRepo;
import com.example.demotda.service.ProductSoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProductSoldServiceImpl implements ProductSoldService {
    private ProductSoldRepo productSoldRepo;
    @Autowired
    public ProductSoldServiceImpl(ProductSoldRepo productSoldRepo) {
        this.productSoldRepo = productSoldRepo;
    }
    @Override
    public Page<ProductSold> listAll(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1, 5);
        return  productSoldRepo.findAll(pageable);
    }
    @Override
    public void save(ProductSold productSold){
        productSoldRepo.save(productSold);
    }
    @Override
    public List<ProductSold> listSoldDay(String today){

        return productSoldRepo.listSoldDay(today);
    }
    @Override
    public List<ProductSold> listSoldWeek(){
        return productSoldRepo.listSoldWeek();
    }
    @Override
    public Long revenueYesterday(String today1, String today2){
        return productSoldRepo.revenueYesterday(today1,today2);
    }
    @Override
    public List<ProductSold> findAll(){
        return productSoldRepo.findAll();
    }
    @Override
    public List<ProductSold> searchDateProductSold(String startDate, String endDate){
        return productSoldRepo.searchDateProductSold(startDate,endDate);

    }
    @Override
    public List<TopSellingg> topSelling() {
        return productSoldRepo.topSelling();
    }

    @Override
    public List<TopUser> topUser() {
        return productSoldRepo.topUser();
    }
    @Override
    public List<ProductSold> boughtProduct(Long id) {
        return productSoldRepo.boughtProduct(id);
    }

    @Override
    public List<Revenue> revenue() {
        return productSoldRepo.revenue();
    }

    @Override
    public long totalSold() {
        return productSoldRepo.totalSold();
    }

    @Override
    public Long countSold(Long id) {
        return productSoldRepo.countSold(id);
    }

    @Override
    public List<ProductSold> findDateProductSold(Date startDate, Date endDate) {
        return productSoldRepo.findDateProductSold(startDate, endDate);
    }
}
