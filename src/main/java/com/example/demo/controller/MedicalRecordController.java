package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.MedicalRecordRequest;
import com.example.demo.dto.response.MedicalRecordResponse;
import com.example.demo.service.MedicalRecordService;
import com.example.demo.utils.GenerateResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("medical-record")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody MedicalRecordRequest medicalRecordRequest,
            @RequestBody String status,
            @RequestHeader(name = "x-token") String token) {
        try {
            MedicalRecordResponse medicalRecordResponse = medicalRecordService.createMedicalRecord(medicalRecordRequest,
                    status);
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Data rekam medis berhasil disimpan",
                    medicalRecordResponse);
        } catch (Exception e) {
            return GenerateResponse.generateResponseEntity(HttpStatus.BAD_REQUEST, "Error: " + e.getMessage());
        }
    }

}
