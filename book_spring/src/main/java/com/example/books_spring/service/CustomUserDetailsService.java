package com.example.books_spring.service;

import com.example.books_spring.entity.Users;
import com.example.books_spring.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findUsersByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: {" + email + "} not found "));

        return new User(
                users.getEmail(),
                users.getPassword(),
                users.getRolesSet()
                        .stream()
                        .map(roles -> new SimpleGrantedAuthority(roles.getRoleType().toString()))
                        .collect(Collectors.toSet())
        );
    }
}
