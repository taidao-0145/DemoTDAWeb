package com.example.demotda.service;

import com.example.demotda.model.Comment;
import com.example.demotda.model.Product;

import java.util.List;


public interface CommentService {
    void saveComment(Comment comment);

    List<Comment> findCommentByProduct(Product product);
}
