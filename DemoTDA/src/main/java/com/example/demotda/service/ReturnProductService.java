package com.example.demotda.service;


import com.example.demotda.model.ReturnProduct;

import java.util.List;

public interface ReturnProductService {
    void saveAll(List<ReturnProduct> returnProducts);
    Long countReturn(Long id);
}
