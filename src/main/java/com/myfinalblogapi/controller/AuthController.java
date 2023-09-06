package com.myfinalblogapi.controller;

import com.myfinalblogapi.entity.Role;
import com.myfinalblogapi.entity.User;
import com.myfinalblogapi.payload.LoginDto;
import com.myfinalblogapi.payload.Signupdto;
import com.myfinalblogapi.repository.RoleRepository;
import com.myfinalblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.util.Password;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
@Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/Signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                loginDto.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authenticate);
        ResponseEntity response = new ResponseEntity<>("user Signed in succesfully", HttpStatus.OK);
        return response;

    }
@PostMapping("/signup")
    public ResponseEntity<?> RegisterUser(@RequestBody Signupdto signupdto) {

        if (userRepository.existsByEmail(signupdto.getEmail())) {
            return new ResponseEntity<>("email already exists ", HttpStatus.BAD_REQUEST);


            }
        if (userRepository.existsByUsername(signupdto.getUsername())) {
            return new ResponseEntity<>("username already taken", HttpStatus.BAD_REQUEST);

        }
        User user = new User();
        user.setEmail(signupdto.getEmail());
        user.setName(signupdto.getName());
        user.setUsername(signupdto.getUsername());
        user.setPassword(passwordEncoder.encode(signupdto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRole(Collections.singleton(role));
        userRepository.save(user);


        return new ResponseEntity<>("Registered successfully",HttpStatus.CREATED);

    }


}
