package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.PatientRegisterRequest;
import com.example.demo.dto.response.PatientResponse;
import com.example.demo.model.Patient;
import com.example.demo.model.User;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientResponse registerPatient(PatientRegisterRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);

        Patient patient = new Patient();
        patient.setBloodType(request.getBloodType());
        patient.setHeight(request.getHeight());
        patient.setWeight(request.getWeight());
        patient.setUser(user);
        try {
            patientRepository.save(patient);
            return new PatientResponse(
                    patient.getId(),
                    patient.getUser(),
                    patient.getHeight(),
                    patient.getWeight(),
                    patient.getBloodType());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: " + e);
        }
    }
}
