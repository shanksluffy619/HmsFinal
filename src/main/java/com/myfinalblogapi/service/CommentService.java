package com.myfinalblogapi.service;

import com.myfinalblogapi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createCommentByPostId(long postId, CommentDto commentDto);

    List<CommentDto> getAllCommentsById(Long postId);

    CommentDto getCommentByPostAndCommentId(Long postId, long commentId);

    CommentDto updateCommetById(long postId, long commentId, CommentDto commentDto);

    void deleteComment(long postId, long commentId);
}
