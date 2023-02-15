package com.example.demotda.repositorie;


import com.example.demotda.model.Comment;
import com.example.demotda.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findCommentByProduct(Product product);
}
