package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.request.MedicalRecordRequest;
import com.example.demo.dto.response.MedicalRecordResponse;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DiseaseRepository;
import com.example.demo.repository.MedicineRepository;

@SpringBootTest
public class MedicalRecordServiceTest {
    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DiseaseRepository diseaseRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @Test
    public void TestCreateMedicalRecord() {
        if (medicineRepository.count() == 0) {
            medicalRecordService.insertMedicineDataDummy();
        }

        if (diseaseRepository.count() == 0) {
            medicalRecordService.insertDiseaseDataDummy();
        }

        MedicalRecordRequest medicalRecordRequest = new MedicalRecordRequest();
        medicalRecordRequest.setAppointment(appointmentRepository.findAppointmentById(1));
        medicalRecordRequest.setDiagnosis("Cough and cold");
        medicalRecordRequest.setDisease(diseaseRepository.findDiseaseById(1));
        medicalRecordRequest.setMedicine(medicineRepository.findMedicineById(1));

        MedicalRecordResponse response = medicalRecordService.createMedicalRecord(medicalRecordRequest,
                "Examine completed");

        Assertions.assertNotNull(response);
    }
}
