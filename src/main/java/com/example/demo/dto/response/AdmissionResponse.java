package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionResponse {

    private Integer id;
    private String admissionDate;
    private String dischargeDate;
    private Integer medicalRecordId;
    private String medicalRecordResult;
    private Integer roomId;
    private String roomName;
}
