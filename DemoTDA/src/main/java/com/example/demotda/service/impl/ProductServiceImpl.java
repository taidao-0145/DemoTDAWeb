package com.example.demotda.service.impl;

import com.example.demotda.dto.ProductDto;
import com.example.demotda.model.ImportProduct;
import com.example.demotda.model.Product;
import com.example.demotda.repositorie.ImportProductRepo;
import com.example.demotda.repositorie.ProductRepo;
import com.example.demotda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    private ImportProductRepo importProductRepo;
    @Autowired
    public ProductServiceImpl(ProductRepo productRepo,ImportProductRepo importProductRepo) {
        this.importProductRepo=importProductRepo;
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
    public void save(ProductDto productDto){
        Date date= new Date();
        Product product= new Product(productDto.getNameproduct(),0,"img/"+productDto.getImg(),
                productDto.getPrice(),productDto.getSale(),date,productDto.getCategory(),productDto.getSupplier());
        productRepo.save(product);
    }
    @Override
    public void updateProduct(ProductDto productDto) {
        Product product= new Product(productDto.getId(),productDto.getNameproduct(),productDto.getSoluong(),productDto.getImg(),
                productDto.getPrice(),productDto.getSale(),productDto.getDateadd(),productDto.getCategory(),productDto.getSupplier());
        Product product1= productRepo.findById(productDto.getId()).orElse(null);
        int num= productDto.getSoluong()-product1.getSoluong();
        ImportProduct importProduct= new ImportProduct();

        if(num>0){
            productRepo.save(product);
        }
        else {
            productRepo.save(product);
        }
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
    public void updateExport(int quantity,Long idProduct){
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
    public void delete(Long id){
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

    @Override
    public void updateImport(int quantity, int canSell,Long id) {
        productRepo.updateImport(quantity,canSell ,id);
    }

    @Override
    public void updateExportProduct(int quantity, Long id) {
        productRepo.updateExport(quantity, id);
    }

    @Override
    public void reportProduct(int quantity, Long id) {
        productRepo.reportProduct(quantity, id);
    }

    @Override
    public List<Product> outOfStock() {
        return productRepo.outOfStock();
    }

}
