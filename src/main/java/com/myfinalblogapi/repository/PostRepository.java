package com.myfinalblogapi.repository;

import com.myfinalblogapi.entity.Post;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findAllByDeletedFalse(Pageable pageable);

    Post findPostByIdAndDeletedFalse(Long id);

}
