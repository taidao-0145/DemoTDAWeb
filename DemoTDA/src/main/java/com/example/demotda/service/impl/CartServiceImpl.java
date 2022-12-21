package com.example.demotda.service.impl;

import com.example.demotda.model.Cart;
import com.example.demotda.model.Product;
import com.example.demotda.model.User;
import com.example.demotda.repositorie.CartRepo;
import com.example.demotda.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private  CartRepo cartRepo;
    @Autowired
    public CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Override
    public void saveCart(Cart cart){
        cartRepo.save(cart);
    }
    @Override
    public Cart findCardById(Long id){
        return cartRepo.findById(id).orElse(null);
    }

    @Override
    public void updateStatusCart(Long id){
        cartRepo.updateStatusCart(id);
    }
    @Override
    public Cart getCartByUser(User user){
        return cartRepo.getCartByUser(user);
    }
    @Override
    public List<Cart> getCartByUserAndStatus(User user, int idStatus){
        return cartRepo.getCartByUserAndStatus(user, idStatus);
    }
    @Override
    public Cart getCartByUserAndProductAndStatus(User user, Product product, int status){
        return cartRepo.getCartByUserAndProductAndStatus(user,product,status);
    }
    @Override
    public void updateCheck(Long userId,Long productId){
        cartRepo.updateCheck(userId,productId);
    }
    @Override
    public void DeleteCart(Long id){
        cartRepo.deleteById(id);
    }

    @Override
    public int countOderById(Long idProduct) {
        return cartRepo.countOderById(idProduct);
    }

}
