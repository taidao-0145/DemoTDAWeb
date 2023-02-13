package com.example.demotda.service.impl;

import com.example.demotda.model.Comment;
import com.example.demotda.model.Product;
import com.example.demotda.repositorie.CommentRepo;
import com.example.demotda.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepo commentRepo;
    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public List<Comment> findCommentByProduct(Product product) {
        return commentRepo.findCommentByProduct(product);
    }
}
