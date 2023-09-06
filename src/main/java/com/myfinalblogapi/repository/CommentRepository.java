package com.myfinalblogapi.repository;

import com.myfinalblogapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllCommentsByPostId(Long id);

    Comment findCommentById(Long commentId);
}
