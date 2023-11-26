package com.example.demotda.controller;

import com.example.demotda.model.Comment;
import com.example.demotda.model.Product;
import com.example.demotda.model.User;
import com.example.demotda.service.CommentService;
import com.example.demotda.service.ProductService;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;

@Controller
public class CommentController {
    private ProductService productService;
    private  CommentService commentService;
    private UserService userService;
    @Autowired
    public CommentController(CommentService commentService,ProductService productService,UserService userService) {
        this.commentService = commentService;
        this.productService=productService;
        this.userService=userService;

    }

    @PostMapping("/comment")
    public String userComment(@RequestParam("productId") Long productId, Principal principal,@RequestParam("comment") String comment){
        System.err.println(productId);
        Product product= productService.getProduct(productId);
        String username= principal.getName();
        User user= userService.findUserByUsername(username);
        Comment cmt= new Comment(comment,user,product,new Date());
        commentService.saveComment(cmt);
        return "redirect:/user/viewProduct?id="+productId;
    }

}
