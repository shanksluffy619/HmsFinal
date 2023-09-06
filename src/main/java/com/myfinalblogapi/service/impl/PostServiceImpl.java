package com.myfinalblogapi.service.impl;


import com.myfinalblogapi.entity.Post;
import com.myfinalblogapi.exception.ResourceNotFounException;
import com.myfinalblogapi.payload.PostDto;
import com.myfinalblogapi.payload.PostRespone;
import com.myfinalblogapi.repository.PostRepository;
import com.myfinalblogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository repository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = MapToEntity(postDto);
        Post post1 = repository.save(post);

        PostDto postDto1 = MapToDto(post1);

        return postDto1;
    }

    @Override
    public PostRespone getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {
//        List<Post> ALlPosts = repository.findAllByDeletedFalse();
//        List<PostDto> AllPosts = ALlPosts.stream().map(post -> MapToDto(post)).collect(Collectors.toList());
Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);

        Page<Post> allByDeletedFalse = repository.findAllByDeletedFalse(pageable);
        List<Post> listOfPosts = allByDeletedFalse.getContent();

        List<PostDto> collect = listOfPosts.stream().map(posts -> MapToDto(posts)).collect(Collectors.toList());

        PostRespone respone = new PostRespone();
        respone.setContent(collect);
        respone.setPageNumber(allByDeletedFalse.getNumber());
        respone.setLast(allByDeletedFalse.isLast());
        respone.setTotalPages(allByDeletedFalse.getTotalPages());
        respone.setTotalElements(allByDeletedFalse.getNumberOfElements());
        respone.setPageSize(allByDeletedFalse.getSize());

        return respone;
    }

    @Override
    public PostDto getPostById(long id) {

        Post post = repository.findPostByIdAndDeletedFalse(id);


        if (post == null) {
            throw new ResourceNotFounException("Post", "postid", id);
        }
        PostDto postDto = MapToDto(post);
        return postDto;

    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {
        Post post = repository.findById(id).orElseThrow(
                () -> new ResourceNotFounException("Post", "PostId", id)

        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = repository.save(post);
        PostDto postDto1 = MapToDto(updatedPost);

        return postDto1;
    }

    @Override
    public void deletePostByid(Long id) {
        Post post = repository.findById(id).orElseThrow(
                () -> new ResourceNotFounException(("Post"), "postId", id)
        );

        post.setDeleted(true);

        repository.save(post);
    }

    private PostDto MapToDto(Post post1) {
        PostDto dto = new PostDto();
        dto.setId(post1.getId());
        dto.setContent(post1.getContent());
        dto.setDescription(post1.getDescription());
        dto.setTitle(post1.getTitle());


        return dto;
    }

    private Post MapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        return post;
    }
}
