package com.anamika.book_ticket.Controller;

import com.anamika.book_ticket.DTO.LoginRequestDTO;
import com.anamika.book_ticket.DTO.LoginResponseDTO;
import com.anamika.book_ticket.DTO.RegisterRequestDTO;
import com.anamika.book_ticket.Entity.userEntity;
import com.anamika.book_ticket.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registernormaluser")
    public ResponseEntity<userEntity> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO){
         return  ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
    }

    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));

    }
}
