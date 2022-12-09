package com.example.demotda.controller.admin;

import com.example.demotda.model.Oder;
import com.example.demotda.repositorie.OderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("oders")
public class OdersController {
    private OderRepo oderRepo;

    @Autowired
    public OdersController(OderRepo oderRepo){
        this.oderRepo=oderRepo;
    }

    @GetMapping
    public String oders(Model model){
        List<Oder> listoder = oderRepo.getOderByIdstatus(1);
        model.addAttribute("listoder", listoder);
        List<Oder> listship = oderRepo.getOderByIdstatus(3);
        model.addAttribute("listship", listship);
        List<Oder> listcancel = oderRepo.getOderByIdstatus(2);
        model.addAttribute("listcancel", listcancel);
        List<Oder> listdone = oderRepo.getOderByIdstatus(4);
        model.addAttribute("listdone", listdone);
        return "admin/oders";
    }
}
