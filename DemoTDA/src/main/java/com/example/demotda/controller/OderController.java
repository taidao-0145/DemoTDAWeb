package com.example.demotda.controller;

import com.example.demotda.model.Oder;
import com.example.demotda.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class OderController {
    private OderService oderService;
    @Autowired
    public OderController(OderService oderService){
        this.oderService=oderService;
    }
    @GetMapping("/oderManagement")
    public String ListOder(Model model){
        List<Oder> listOder = oderService.getOderByStatus(1);
        model.addAttribute("listOder", listOder);
        List<Oder> listShip = oderService.getOderByStatus(3);
        model.addAttribute("listShip", listShip);
        List<Oder> listCancel = oderService.getOderByStatus(2);
        model.addAttribute("listCancel", listCancel);
        List<Oder> listDone = oderService.getOderByStatus(4);
        model.addAttribute("listDone", listDone);
        return "admin/orders";
    }
    @GetMapping("/ship")
    public String Shipping(@RequestParam("id") Long id){
        oderService.shipOder(id);
        return "redirect:/oderManagement";
    }
    @GetMapping("/doneShip")
    public String DoneShip(@RequestParam("id") Long id){
        oderService.doneShip(id);
        return "redirect:/oderManagement";
    }

    @GetMapping("/oderShip")
    public String ViewOderShip(Principal principal, Model model){
        String username= principal.getName();
        List<Oder> listShip= oderService.getOderByUsernameAndIdStatus(username,3);
        model.addAttribute("listShip",listShip);
        return "user/odership";
    }

    @GetMapping("/oder")
    public String ViewOderUser(Principal principal, Model model){
        String username = principal.getName();
        List<Oder> listOderUser= oderService.getOderByUsernameAndIdStatus(username,1);
        model.addAttribute("listOderUser",listOderUser);
        return "user/oder";
    }

    @GetMapping("/oderCancel")
    public String ViewOderCancelUser(Principal principal, Model model){
        String username= principal.getName();
        List<Oder> listCancel= oderService.getOderByUsernameAndIdStatus(username,2);
        model.addAttribute("listCancel", listCancel);
        return "user/listodercancel";
    }

    @GetMapping("/cancelOder")
    public String cancelOder(@RequestParam("id") Long idOder){
        oderService.cancelOder(idOder);
        return "redirect:/odercancel";
    }
}
