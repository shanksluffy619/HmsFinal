package com.myfinalblogapi.service;

import com.myfinalblogapi.payload.PostDto;
import com.myfinalblogapi.payload.PostRespone;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostRespone getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);

  PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, Long id);

    void deletePostByid(Long id);
}
