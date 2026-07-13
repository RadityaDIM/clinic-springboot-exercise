package com.example.demo.dto.request;

import java.util.List;

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
    private Integer appointmentId;
    private String diagnosis;
    private Integer diseaseId;

    private List<MedicineRequest> medicine;
}
