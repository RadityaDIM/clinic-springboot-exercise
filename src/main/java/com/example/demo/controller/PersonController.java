package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.utils.GenerateResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PutMapping("update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Person person,
            @RequestHeader(name = "x-token") String token) {
        try {
            personService.updatePerson(id, person);
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Data berhasil diupdate.");
        } catch (Exception e) {
            return GenerateResponse.generateResponseEntity(HttpStatus.BAD_REQUEST, "Error : " + e.getMessage());
        }
    }
}
