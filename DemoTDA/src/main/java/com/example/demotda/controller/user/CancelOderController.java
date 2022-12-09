package com.example.demotda.controller.user;

import com.example.demotda.repositorie.OderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/canceloder")
public class CancelOderController {
    private OderRepo oderRepo;

    @Autowired
    public CancelOderController(OderRepo oderRepo){
        this.oderRepo=oderRepo;
    }

    @GetMapping
    public String canceloder(@RequestParam("id") Long id){
        oderRepo.canceloder(id);

        return "redirect:/odercancel";
    }
}
