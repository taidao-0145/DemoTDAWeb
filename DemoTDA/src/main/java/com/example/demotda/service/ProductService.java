package com.example.demotda.service;

import com.example.demotda.model.Product;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {

    List<Product> listProduct();
    Product getProduct(Long id);
    void save(Product product);
    Page<Product> listAll(int pageNumber);
    Page<Product> listStoreAll(int pageNumber);
    void saveProduct(Product product);
    void UpdateExport(int quantity,Long idProduct);
    List<Product> listHet();
    List<Product> checkInventory();
    void Delete(Long id);
    List<Product> listNew();
    List<Product> listSale();
    List<Product> searchProduct(String keyword);

}

