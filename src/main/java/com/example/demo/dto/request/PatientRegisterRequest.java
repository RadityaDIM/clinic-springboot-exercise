package com.example.demo.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegisterRequest {
    @NotNull(message = "ID User harus ada.")
    private Integer userId;
    @NotNull(message = "Data golongan darah harus ada.")
    private String bloodType;
    @NotNull(message = "Data tinggi badan harus ada.")
    private Integer height;
    @NotNull(message = "Data berat badan harus ada.")
    private Integer weight;
}
