package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.AdmissionRequest;
import com.example.demo.dto.response.AdmissionResponse;
import com.example.demo.model.Admission;
import com.example.demo.model.MedicalRecord;
import com.example.demo.model.Room;
import java.util.Optional;
import com.example.demo.repository.AdmissionRepository;
import com.example.demo.repository.MedicalRecordRepository;
import com.example.demo.repository.RoomRepository;

@Service
public class AdmissionService {
        @Autowired
        AdmissionRepository admissionRepository;

        @Autowired
        RoomRepository roomRepository;

        @Autowired
        MedicalRecordRepository medicalRecordRepository;

        public AdmissionResponse createAdmission(AdmissionRequest admissionRequest) {
                MedicalRecord medicalRecord = medicalRecordRepository.findById(admissionRequest.getMedicalRecordId())
                                .orElseThrow(() -> new RuntimeException("Data rekam medis tidak ditemukan"));

                // Cek apakah sudah ada admission untuk medical record ini
                Optional<Admission> existingAdmission = admissionRepository.findByMedicalRecord(medicalRecord);
                if (existingAdmission.isPresent()) {
                        throw new RuntimeException("Data rawat inap untuk rekam medis dengan ID "
                                        + admissionRequest.getMedicalRecordId() + " sudah ada.");
                }

                Room room = roomRepository.findById(admissionRequest.getRoomId())
                                .orElseThrow(() -> new RuntimeException("Data kamar tidak ditemukan"));

                Admission admission = new Admission();
                admission.setAdmissionDate(admissionRequest.getAdmissionDate());
                admission.setMedicalRecord(medicalRecord);
                admission.setRoom(room);

                Admission saveAdmission = admissionRepository.save(admission);

                return new AdmissionResponse(
                                saveAdmission.getId(),
                                saveAdmission.getAdmissionDate().toString(),
                                saveAdmission.getDischargeDate(),
                                saveAdmission.getMedicalRecord().getId(),
                                saveAdmission.getMedicalRecord().getResult(),
                                saveAdmission.getRoom().getId(),
                                saveAdmission.getRoom().getName());
        }
}
