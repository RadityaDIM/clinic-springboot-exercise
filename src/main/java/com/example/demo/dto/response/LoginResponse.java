package com.example.demo.dto.response;

import com.example.demo.model.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Integer id;
    private String username;
    private Person person;
}
