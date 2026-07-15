package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.RegisterResponse;
import com.example.demo.service.AuthService;
import com.example.demo.utils.GenerateResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController // KALO DALAM BENTUK VIEW PAKENYA CONTROLLER
@RequestMapping // domain/auth
@RequiredArgsConstructor // BIAR GAUSAH PAKAI AUTOWIRED PAS DEPENDENCY INJECTION
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {

        try {
            LoginResponse loginResponse = authService.login(loginRequest);
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Login Berhasil", loginResponse);
        } catch (Exception e) {
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, e.getMessage());
        }
    }

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest) {
        try {
            RegisterResponse registerResponse = authService.register(registerRequest);
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Register Berhasil", registerResponse);
        } catch (Exception e) {
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, e.getMessage());
        }
    }

}
