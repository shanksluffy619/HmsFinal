package com.myfinalblogapi.security;

import com.myfinalblogapi.entity.Role;
import com.myfinalblogapi.entity.User;
import com.myfinalblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

@Autowired
private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByusernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("user not found with username or email : " + usernameOrEmail)
        );

        return  new
        org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapToAuthorities(user.getRole()));


    }

    private Collection<? extends GrantedAuthority> mapToAuthorities(Set<Role> roles) {
        List<SimpleGrantedAuthority> rolee = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
return rolee;
    }


}
