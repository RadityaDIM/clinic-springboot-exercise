package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.request.MedicalRecordRequest;
import com.example.demo.dto.request.MedicineRequest;
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

        MedicineRequest medicineRequest = new MedicineRequest();
        medicineRequest.setName("Paracetamol");
        medicineRequest.setQuantity(2);

        List<MedicineRequest> medicineList = new ArrayList<>();
        medicineList.add(medicineRequest);

        MedicalRecordRequest medicalRecordRequest = new MedicalRecordRequest();
        medicalRecordRequest.setAppointmentId(1);
        medicalRecordRequest.setDiagnosis("Cough and cold");
        medicalRecordRequest.setDiseaseId(1);
        medicalRecordRequest.setMedicine(medicineList);

        MedicalRecordResponse response = medicalRecordService.createMedicalRecord(medicalRecordRequest,
                "Examine completed");

        Assertions.assertNotNull(response);
    }
}
