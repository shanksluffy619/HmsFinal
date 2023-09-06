package com.myfinalblogapi.repository;

import com.myfinalblogapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByusernameOrEmail(String username,String email);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
