package com.myfinalblogapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String body;


    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String name;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id", nullable = false)
        private Post post;
}
