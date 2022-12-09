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
@RequestMapping("/odercancel")
public class ListOderCancel {
    private OderRepo oderRepo;
    @Autowired
    public ListOderCancel(OderRepo oderRepo){
        this.oderRepo=oderRepo;
    }
    @GetMapping
    public String cancel(Principal principal, Model model){
        String username= principal.getName();
        List<Oder> listcancel= oderRepo.getOderByUsernameAndIdstatus(username,2);
        model.addAttribute("listcancel", listcancel);
        return "user/listodercancel";
    }
}
