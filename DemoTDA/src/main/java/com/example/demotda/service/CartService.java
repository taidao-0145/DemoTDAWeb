package com.example.demotda.service;

import com.example.demotda.model.Cart;
import com.example.demotda.model.Product;
import com.example.demotda.model.User;

import java.util.List;

public interface CartService {

    void saveCart(Cart cart);
    Cart findCardById(Long id);
    void updateStatusCart(Long id);
    Cart getCartByUser(User user);
    List<Cart> getCartByUserAndStatus(User user, int idStatus);
    Cart getCartByUserAndProductAndStatus(User user, Product product, int status);
    void updateCheck(Long userId,Long productId);
    void DeleteCart(Long id);
    int countOderById(Long idProduct);
}
