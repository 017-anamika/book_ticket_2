package com.anamika.book_ticket.Service;

import com.anamika.book_ticket.DTO.LoginRequestDTO;
import com.anamika.book_ticket.DTO.LoginResponseDTO;
import com.anamika.book_ticket.DTO.RegisterRequestDTO;
import com.anamika.book_ticket.Entity.User;
import com.anamika.book_ticket.JWT.JWTService;
import com.anamika.book_ticket.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service

public class AuthenticationService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JWTService jwtService;

    private AuthenticationManager authenticationManager;
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }



    public User registerNormalUser(RegisterRequestDTO registerRequestDTO){
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("Username is already in use");
        }
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setEmail(registerRequestDTO.getEmail());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User registerAdminUser(RegisterRequestDTO registerRequestDTO){
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("Username is already in use");
        }
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setEmail(registerRequestDTO.getEmail());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(()->new RuntimeException("Username not found"));

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = jwtService.generateToken(user);
        return LoginResponseDTO.builder()
                .jwtToken(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
}
