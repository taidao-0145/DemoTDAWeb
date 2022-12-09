package com.example.demotda.controller.user;

import com.example.demotda.model.Oder;
import com.example.demotda.repositorie.OderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/odership")
public class OderShipController {
    private OderRepo oderRepo;
    @Autowired
    public OderShipController(OderRepo oderRepo){
        this.oderRepo=oderRepo;
    }

    @GetMapping
    public String odership(Principal principal, Model model){
        String username= principal.getName();
        List<Oder> listship= oderRepo.getOderByUsernameAndIdstatus(username,3);
        model.addAttribute("listship",listship);
        return "user/odership";
    }
}
