package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.AppointmentRequest;
import com.example.demo.dto.response.AppointmentResponse;
import com.example.demo.dto.response.AppointmentWithPatientAndDoctor;
import com.example.demo.service.AppointmentService;
import com.example.demo.utils.GenerateResponse;
import com.example.demo.utils.enums.Status;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody AppointmentRequest appointmentRequest,
            @RequestHeader(name = "x-token") String token) {
        try {
            AppointmentResponse appointmentResponse = appointmentService.bookAppointment(appointmentRequest);
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Jadwal Appointment berhasil dibuat.",
                    appointmentResponse);
        } catch (Exception e) {
            return GenerateResponse.generateResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Status status,
            @RequestHeader(name = "x-token") String token) {
        try {
            AppointmentResponse appointmentResponse = appointmentService.manageAppointment(id, status);
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Data appointment berhasil diubah.",
                    appointmentResponse);
        } catch (Exception e) {
            return GenerateResponse.generateResponseEntity(HttpStatus.BAD_REQUEST,
                    "Data appointment gagal diubah, error: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> displayAll(@RequestHeader(name = "x-token") String token) {
        try {
            List<AppointmentWithPatientAndDoctor> listAppointment = appointmentService
                    .displayAllAppointmentWithPatientAndDoctor();
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Data berhasil didapatkan.", listAppointment);
        } catch (Exception e) {
            return GenerateResponse.generateResponseEntity(HttpStatus.BAD_REQUEST, "Error: " + e);
        }
    }

}
