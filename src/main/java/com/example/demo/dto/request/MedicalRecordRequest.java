package com.example.demo.dto.request;

import com.example.demo.model.Appointment;
import com.example.demo.model.Disease;
import com.example.demo.model.Medicine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordRequest {
    private Appointment appointment;
    private String diagnosis;
    private Disease disease;
    private Medicine medicine;
}
