package com.example.demotda.repositorie;

import com.example.demotda.model.Cart;
import com.example.demotda.model.Product;
import com.example.demotda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    List<Cart> getCartByUsername(String username);

    List<Cart> getCartByUserAndStatus(User user, int status);
    List<Cart> getCartByUsernameAndIdproduct(String username, Long idproduct);

    Cart getCartByUserAndProductAndStatus(User user, Product product,int status);
    Cart getCartByUser(User user);
    @Transactional
    @Modifying
    @Query(value="UPDATE cart set quantity=quantity+1  where user_id=? and product_id=?", nativeQuery=true)
    void updateCheck(Long userId,Long productId);

    @Transactional
    @Modifying
    @Query(value="UPDATE cart set status=0 where id=?", nativeQuery=true)
    void updateStatusCart(Long id);

    @Transactional
    @Query(value="SELECT count(*) FROM cart WHERE product_id=? and status =0;", nativeQuery=true)
    int countOderById(Long idProduct);

    @Transactional
    @Modifying
    @Query(value="UPDATE cart set quantity=quantity+1 where id=?", nativeQuery=true)
    void updatePlusQuantity(Long id);

    @Transactional
    @Modifying
    @Query(value="UPDATE cart set quantity=quantity-1 where id=?", nativeQuery=true)
    void updateMinusQuantity(Long id);
}
