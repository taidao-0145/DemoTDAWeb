package com.example.demotda.service;

import com.example.demotda.model.Cart;
import com.example.demotda.model.Oder;

import java.util.List;

public interface OderService {
     void saveOder(Oder oder);
     Oder findOderById(Long id);
     void cancelOder(Long id);
     void shipOder(Long id);
     void doneShip(Long id);
     List<Oder> getOderByStatus(int id);
     Oder findOderByCart(Cart cart);
     List<Oder> getOderByUsernameAndIdStatus(String username, int id);
     void deleteOder(Long id);
     long countOder();
     long countOderShip();

}
