package com.example.demotda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/allBooks";
    }
}
