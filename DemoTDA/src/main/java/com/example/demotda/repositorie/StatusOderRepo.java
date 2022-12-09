package com.example.demotda.repositorie;

import com.example.demotda.model.StatusOder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOderRepo extends JpaRepository<StatusOder,Integer> {

}
