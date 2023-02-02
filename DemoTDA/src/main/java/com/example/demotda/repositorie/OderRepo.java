package com.example.demotda.repositorie;

import com.example.demotda.model.Cart;
import com.example.demotda.model.Oder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface OderRepo extends JpaRepository<Oder,Long> {
    List<Oder> getOderByUsername(String username);

    @Transactional
    @Modifying
    @Query(value="UPDATE oder set idstatus=2 where  id=?", nativeQuery=true)
    void cancelOder(Long id);

    List<Oder> getOderByUsernameAndIdstatus(String username, int id);
    List<Oder> getOderByIdstatus(int id);

    List<Oder> getOderByCartAndIdstatus(Cart cart, int idStatus);

    List<Oder> getOderByIdproduct(Long id);

    Oder findOderByCart(Cart cart);
    Oder findOderById(Long id);

    @Transactional
    @Modifying
    @Query(value="UPDATE oder set idstatus=3 where  id=?", nativeQuery=true)
    void shipOder(Long id);

    @Transactional
    @Modifying
    @Query(value="UPDATE oder set idstatus=4 where  id=?", nativeQuery=true)
    void doneShip(Long id);

    @Transactional
    @Query(value="SELECT count(*) FROM oder WHERE idstatus =1;", nativeQuery=true)
    long countOder();

    @Transactional
    @Query(value="SELECT count(*) FROM oder WHERE idstatus =3;", nativeQuery=true)
    long countOderShip();

    @Transactional
    @Modifying
    @Query(value="SELECT * FROM oder where (date) between ? and now() limit 0,3", nativeQuery=true)
    List<Oder> oderNew(String today);
}
