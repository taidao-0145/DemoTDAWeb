package com.example.demotda.controller.user;

import com.example.demotda.model.Oder;
import com.example.demotda.repositorie.OderRepo;
import com.example.demotda.repositorie.StatusOderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/oder")
public class OderController {
    private OderRepo oderRepo;
    private StatusOderRepo statusOderRepo;

    @Autowired
    public OderController(OderRepo oderRepo,StatusOderRepo statusOderRepo){
        this.oderRepo=oderRepo;
        this.statusOderRepo=statusOderRepo;
    }

    @GetMapping
    public String viewoder(Principal principal, Model model){
        String username = principal.getName();
        List<Oder> listoder= oderRepo.getOderByUsernameAndIdstatus(username,1);
        model.addAttribute("listoder",listoder);
        return "user/oder";
    }
}
