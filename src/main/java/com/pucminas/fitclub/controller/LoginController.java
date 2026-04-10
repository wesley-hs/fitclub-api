package com.pucminas.fitclub.controller;

import com.pucminas.fitclub.service.LoginService;
import com.pucminas.fitclub.service.dto.AutheticationRequest;
import com.pucminas.fitclub.service.dto.AuthResponse;
import com.pucminas.fitclub.service.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AutheticationRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
