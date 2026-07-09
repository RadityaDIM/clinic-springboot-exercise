package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPersonResponse {
    private Integer id;
    private String username;
    private String email;
    private String firstName;
    private String address;
}
