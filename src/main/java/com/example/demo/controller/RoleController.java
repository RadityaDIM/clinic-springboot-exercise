package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import com.example.demo.utils.GenerateResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    // @GetMapping("/{id}")
    // public Role findRoleById(@PathVariable Integer id, @RequestHeader(name =
    // "x-token") String token) {
    // return roleService.findRoleById(id);
    // }

    @GetMapping("{id}")
    public ResponseEntity<Object> findRoleById(@PathVariable Integer id,
            @RequestHeader(name = "x-token") String token) {

        Role role = roleService.findRoleById(id);
        if (role != null) {
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Data berhasil ditemukan",
                    roleService.findRoleById(id));
        } else {
            return GenerateResponse.generateResponseEntity(HttpStatus.OK, "Data tidak ditemukan"); // MESKIPUN GAGAL
                                                                                                   // HTTP STATUS TETAP
                                                                                                   // SET OK -> KARENA
                                                                                                   // REQUEST BERARTI
                                                                                                   // BERHASIL
                                                                                                   // DIJALANKAN
        }

    }
}
