package com.example.demo.dto.response;

import javax.management.relation.Role;

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
    private String token;
    private Integer roleId;
}
