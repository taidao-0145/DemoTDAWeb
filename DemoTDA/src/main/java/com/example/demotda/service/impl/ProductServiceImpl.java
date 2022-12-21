package com.example.demotda.service.impl;

import com.example.demotda.model.Product;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    @Autowired
    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Override
    public List<Product> listProduct(){
        return productRepo.findAll();
    }
    @Override
    public Product getProduct(Long id){
        return productRepo.findById(id).orElse(null);
    }
    @Override
    public void save(Product product){
        productRepo.save(product);
    }
    @Override
    public Page<Product> listAll(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1, 5);
        return  productRepo.findAll(pageable);
    }
    @Override
    public Page<Product> listStoreAll(int pageNumber){
        Pageable pageableStore = PageRequest.of(pageNumber-1, 6);
        return  productRepo.findAll(pageableStore);
    }
    @Override
    public void saveProduct(Product product){
        productRepo.save(product);
    }
    @Override
    public void UpdateExport(int quantity,Long idProduct){
        productRepo.exportProduct(quantity,idProduct);
    }
    @Override
    public List<Product> listHet(){
        return productRepo.checkAmount();
    }
    @Override
    public List<Product> checkInventory(){
        return productRepo.checkInventory();
    }
    @Override
    public void Delete(Long id){
        productRepo.deleteById(id);
    }
    @Override
    public List<Product> listNew(){
        return productRepo.listNew();
    }
    @Override
    public List<Product> listSale(){
        return productRepo.listSale();
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepo.searchProduct(keyword);
    }

}
