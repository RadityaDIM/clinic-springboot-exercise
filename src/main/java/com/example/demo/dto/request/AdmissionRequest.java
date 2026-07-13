package com.example.demo.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdmissionRequest {
    @NotNull(message = "Tanggal rawat inap harus ada.")
    private LocalDateTime admissionDate;

    @NotNull(message = "Data rekam medis harus ada.")
    private Integer medicalRecordId;

    @NotNull(message = "Data kamar harus ada.")
    private Integer roomId;
}
