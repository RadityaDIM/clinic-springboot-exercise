package com.example.demo.dto.response;

import javax.validation.constraints.NotNull;

import com.example.demo.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private Integer id;
    private User user;
    private Integer height;
    private Integer weight;
    private String bloodType;
}
