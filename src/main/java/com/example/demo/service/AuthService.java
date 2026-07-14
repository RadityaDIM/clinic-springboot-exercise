package com.example.demo.service;

import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.RegisterResponse;
import com.example.demo.model.Person;
import com.example.demo.model.User;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AuthService {
    private final RoleRepository roleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    AuthService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        Person person = new Person();
        person.setFirstName(registerRequest.getFirstName());
        person.setLastName(registerRequest.getLastName());

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(roleRepository.findTopByOrderByLevelAsc());

        user.setPerson(person);
        person.setUser(user);

        // save user biar dpt id dan data lainnya
        User savedUser = userRepository.save(user);

        // isi response
        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail());
    }

    public LoginResponse login(LoginRequest loginRequest) {

        User user = userRepository.findUserByUsername(loginRequest.getUsername());

        if (user != null && passwordEncoder
                .matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponse(user.getId(), user.getUsername(), user.getPerson());
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }

}
