package com.myfinalblogapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePassword {
    public static void main(String[] args) {
        PasswordEncoder encode = new BCryptPasswordEncoder();
        String admin = encode.encode("mike");
        System.out.println(admin);
    }

}
