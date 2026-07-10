package com.example.demo.dto.response;

import java.time.LocalDate;

import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.utils.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentWithPatientAndDoctor {
    private LocalDate appointmentDate;
    private String patientName;
    private String doctorName;
    private String status;
}
