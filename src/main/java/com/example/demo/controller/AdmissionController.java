package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.AdmissionRequest;
import com.example.demo.dto.response.AdmissionResponse;
import com.example.demo.service.AdmissionService;
import com.example.demo.utils.GenerateResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("admission")
@RequiredArgsConstructor
public class AdmissionController {
    private final AdmissionService admissionService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody AdmissionRequest admissionRequest,
            @RequestHeader(name = "x-token") String token) {
        try {
            AdmissionResponse admissionResponse = admissionService.createAdmission(admissionRequest);
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Data rawat inap berhasil dibuat.",
                    admissionResponse);
        } catch (Exception e) {
            return GenerateResponse.generateResponseEntity(HttpStatus.BAD_REQUEST, "Error: " + e.getMessage());
        }
    }

}
