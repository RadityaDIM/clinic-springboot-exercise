package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.PatientRegisterRequest;
import com.example.demo.dto.response.PatientResponse;
import com.example.demo.service.PatientService;
import com.example.demo.utils.GenerateResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequiredArgsConstructor
@RequestMapping("patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody PatientRegisterRequest request,
            @RequestHeader(name = "x-token") String token) {
        try {
            PatientResponse response = patientService.registerPatient(request);
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Data pasien berhasil disimpan", response);
        } catch (Exception e) {
            return GenerateResponse.generateResponseEntity(HttpStatus.BAD_REQUEST,
                    "Data pasien gagal disimpan, error: " + e.getMessage());
        }
    }

}
