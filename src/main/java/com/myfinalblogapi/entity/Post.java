package com.myfinalblogapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String title;


    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private  String description;

    @Column(nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

}
