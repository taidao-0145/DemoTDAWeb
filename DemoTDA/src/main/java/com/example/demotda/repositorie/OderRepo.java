package com.example.demotda.repositorie;

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
    void canceloder(Long id);

    List<Oder> getOderByUsernameAndIdstatus(String username, int id);
    List<Oder> getOderByIdstatus(int id);

    List<Oder> getOderByIdproduct(Long id);

    @Transactional
    @Modifying
    @Query(value="UPDATE oder set idstatus=3 where  id=?", nativeQuery=true)
    void shiploder(Long id);

    @Transactional
    @Modifying
    @Query(value="UPDATE oder set idstatus=4 where  id=?", nativeQuery=true)
    void doneship(Long id);

    @Transactional
    @Modifying
    @Query(value="SELECT count(*) FROM oder WHERE idproduct =?;", nativeQuery=true)
    int countquantityoder();
}
