package com.example.demo.dto.request;

import java.time.LocalDate;

import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.utils.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private Patient patient;
    private Doctor doctor;
    private LocalDate appointmentDate;
    private Status status;

}
