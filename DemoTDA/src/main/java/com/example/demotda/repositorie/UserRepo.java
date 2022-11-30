package com.example.demotda.repositorie;

import com.example.demotda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    List<User> findByUsername(String username);
    User findUserByUsername(String username);
    User findUserByUsernameAndPass(String username, String pass);
}
