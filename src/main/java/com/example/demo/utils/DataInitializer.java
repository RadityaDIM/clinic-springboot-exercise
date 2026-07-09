package com.example.demo.utils;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            List<Role> roles = Arrays.asList(
                    new Role(null, "ROLE_USER", 1),
                    new Role(null, "ROLE_DOCTOR", 2),
                    new Role(null, "ROLE_ADMIN", 3));
            roleRepository.saveAll(roles);
            System.out.println("Initial roles created: USER, DOCTOR, ADMIN");
        } else {
            System.out.println("Roles already exist in the database.");
        }
    }
}