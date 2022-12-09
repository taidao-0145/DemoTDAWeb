package com.example.demotda.repositorie;

import com.example.demotda.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {
    List<Cart> getCartByUsername(String username);

    List<Cart> getCartByUsernameAndIdproduct(String username, Long idproduct);

    @Transactional
    @Modifying
    @Query(value="UPDATE cart set quantity=quantity+1 ,total=total+price where username=? and idproduct=?", nativeQuery=true)
    void updatecheck(String username,Long id);
}
