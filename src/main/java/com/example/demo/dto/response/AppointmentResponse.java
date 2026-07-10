package com.example.demo.dto.response;

import java.time.LocalDate;

import com.example.demo.utils.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Integer id;
    private LocalDate appointmentDate;
    private Status status;

}
