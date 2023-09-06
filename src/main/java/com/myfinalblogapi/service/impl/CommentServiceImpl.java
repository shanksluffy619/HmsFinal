package com.myfinalblogapi.service.impl;

import com.myfinalblogapi.entity.Comment;
import com.myfinalblogapi.entity.Post;
import com.myfinalblogapi.exception.ResourceNotFounException;
import com.myfinalblogapi.payload.CommentDto;
import com.myfinalblogapi.repository.CommentRepository;
import com.myfinalblogapi.repository.PostRepository;
import com.myfinalblogapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public CommentDto createCommentByPostId(long postId, CommentDto commentDto) {

        Post post = postRepository.findPostByIdAndDeletedFalse(postId);
        if (post == null) {
            throw new ResourceNotFounException("Post", "PostId", postId);
        }

        Comment comment = MaptoEntity(commentDto);
        comment.setPost(post);

        Comment comment1 = commentRepository.save(comment);
        CommentDto dto = MapToDto(comment1);

        return dto;
    }

    @Override
    public List<CommentDto> getAllCommentsById(Long postId) {
        Post post = postRepository.findPostByIdAndDeletedFalse(postId);
        if (post == null) {
            throw new ResourceNotFounException("Post", "postId", postId);
        }

        Long id = post.getId();
        List<Comment> allCommentsByPostId = commentRepository.findAllCommentsByPostId(id);
        List<CommentDto> collect = allCommentsByPostId.stream().map(comments -> MapToDto(comments)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public CommentDto getCommentByPostAndCommentId(Long postId, long commentId) {
        Post post = postRepository.findPostByIdAndDeletedFalse(postId);
        if (post == null) {
            throw new ResourceNotFounException("Post", "postId", postId);
        }

        Comment comment = commentRepository.findCommentById(commentId);
        if (comment == null) {
            throw new ResourceNotFounException("Comment", "commentId", commentId);
        } else if (!comment.getPost().getId().equals(post.getId())) {
            throw new ResourceNotFounException("Comment does not belong to this post", "commentId", commentId);
        }

        CommentDto dto = MapToDto(comment);
        return dto;
    }

    @Override
    public CommentDto updateCommetById(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findPostByIdAndDeletedFalse(postId);
        if (post == null) {
            throw new ResourceNotFounException("Post", "postId", postId);
        }

        Comment comment = commentRepository.findCommentById(commentId);
        if (comment == null) {
            throw new ResourceNotFounException("Comment", "commentId", commentId);
        } else if (!comment.getPost().getId().equals(post.getId())) {
            throw new ResourceNotFounException("Comment does not belong to this post", "commentId", commentId);
        }

        comment.setPost(post);
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        Comment comment1 = commentRepository.save(comment);
        CommentDto dto = MapToDto(comment1);

        return dto;
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findPostByIdAndDeletedFalse(postId);
        if (post == null) {
            throw new ResourceNotFounException("Post", "postId", postId);
        }
        Comment comment = commentRepository.findCommentById(commentId);
        if (comment == null) {
            throw new ResourceNotFounException("Comment", "commentId", commentId);
        } else if (!comment.getPost().getId().equals(post.getId())) {
            throw new ResourceNotFounException("Comment does not belong to this post", "commentId", commentId);
        }
        commentRepository.delete(comment);

    }

    private CommentDto MapToDto(Comment comments) {
        CommentDto dto = modelMapper.map(comments, CommentDto.class);
//        CommentDto dto = new CommentDto();
//        dto.setId(comments.getId());
//        dto.setName(comments.getName());
//        dto.setEmail(comments.getEmail());
//        dto.setBody(comments.getBody());


        return dto;
    }

    private Comment MaptoEntity(CommentDto commentDto) {

        Comment comment = modelMapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());


        return comment;
    }
}
