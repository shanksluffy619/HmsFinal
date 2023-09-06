package com.myfinalblogapi.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Users")
public class User {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String  password;


    @Column(nullable = false)
    private String email;

@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
@JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
        private Set<Role> role = new HashSet<>();

}
