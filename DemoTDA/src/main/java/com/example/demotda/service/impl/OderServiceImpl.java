package com.example.demotda.service.impl;

import com.example.demotda.model.Cart;
import com.example.demotda.model.Oder;
import com.example.demotda.repositorie.OderRepo;
import com.example.demotda.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OderServiceImpl implements OderService {

    private OderRepo oderRepo;
    @Autowired
    public OderServiceImpl(OderRepo oderRepo) {
        this.oderRepo = oderRepo;
    }

    @Override
    public void saveOder(Oder oder) {
        oderRepo.save(oder);
    }

    @Override
    public Oder findOderById(Long id) {
        return oderRepo.findOderById(id);
    }

    @Override
    public void cancelOder(Long id) {
        oderRepo.cancelOder(id);
    }

    @Override
    public void shipOder(Long id) {
        oderRepo.shipOder(id);
    }

    @Override
    public void doneShip(Long id) {
        oderRepo.doneShip(id);
    }

    @Override
    public List<Oder> getOderByStatus(int id) {
        return oderRepo.getOderByIdstatus(id);
    }

    @Override
    public Oder findOderByCart(Cart cart) {
        return oderRepo.findOderByCart(cart);
    }
    @Override
    public List<Oder> getOderByUsernameAndIdStatus(String username, int id) {
        return oderRepo.getOderByUsernameAndIdstatus(username,id);
    }

    @Override
    public void deleteOder(Long id) {
        oderRepo.deleteById(id);
    }

    @Override
    public long countOder() {
        return oderRepo.countOder();
    }

    @Override
    public long countOderShip() {
        return oderRepo.countOderShip();
    }

    @Override
    public List<Oder> oderNew(String today) {
        return oderRepo.oderNew(today);
    }

}
