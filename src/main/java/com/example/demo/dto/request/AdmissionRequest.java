package com.example.demo.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdmissionRequest {
    @NotNull(message = "Tanggal rawat inap harus ada.")
    private LocalDate admissionDate;

    @NotNull(message = "Data rekam medis harus ada.")
    private Integer medicalRecordId;

    @NotNull(message = "Data kamar harus ada.")
    private Integer roomId;
}
