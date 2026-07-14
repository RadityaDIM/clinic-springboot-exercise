package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}
