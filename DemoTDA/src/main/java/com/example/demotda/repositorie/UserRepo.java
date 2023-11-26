package com.example.demotda.repositorie;

import com.example.demotda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findByUsername(String username);
    User findUserByUsername(String username);
    User findUserByUsernameAndPass(String username, String pass);

    User findByUsernameAndEmail(String username, String email);

    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value="UPDATE user set pass=? where  username=?", nativeQuery=true)
    void changepassword(String pass,String username);

    User findUserById(Long id);

    @Transactional
    @Modifying
    @Query(value="select * from demotda.user where username like %?%", nativeQuery=true)
    List<User> search(String name);

}
