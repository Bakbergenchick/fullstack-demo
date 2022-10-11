package com.example.books_spring.controller;

import com.example.books_spring.entity.RoleType;
import com.example.books_spring.repo.RoleRepository;
import com.example.books_spring.entity.Roles;
import com.example.books_spring.entity.Users;
import com.example.books_spring.payload.SignInDTO;
import com.example.books_spring.payload.SignUpDTO;
import com.example.books_spring.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(
            @RequestBody SignUpDTO signUpUser

    ){
        if (userRepository.existsUsersByEmail(signUpUser.getEmail())){
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        Users user = new Users();

        user.setEmail(signUpUser.getEmail());
        user.setSurname(signUpUser.getSurname());
        user.setPassword(passwordEncoder.encode(signUpUser.getPassword()));
        user.setName(signUpUser.getName());

        Roles role = roleRepository.findByRoleType(RoleType.ROLE_USER).get();
        user.setRolesSet(Collections.singleton(role));

        Users saved_user = userRepository.save(user);

        return new ResponseEntity<>(saved_user, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(
            @RequestBody SignInDTO signInUser
            ){
        Authentication authentication;

        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(signInUser.getEmail(), signInUser.getPassword()));
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid credentials");
        }

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        return new ResponseEntity<>("You've been logged in", HttpStatus.OK);
    }

    @GetMapping("/welcome")
    public String welcome(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.isAuthenticated()){
            System.out.println(authentication.getAuthorities());
        }
        return "You're welcome! Please sign in to get access to resources";
    }

    @GetMapping("/dashboard")
    public void dash(Principal principal){

    }
}
