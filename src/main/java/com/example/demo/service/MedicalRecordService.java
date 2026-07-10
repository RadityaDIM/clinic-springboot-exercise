package com.example.demo.service;

import com.example.demo.repository.DiseaseRepository;
import com.example.demo.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.MedicalRecordRequest;
import com.example.demo.dto.response.MedicalRecordResponse;
import com.example.demo.model.Disease;
import com.example.demo.model.MedicalRecord;
import com.example.demo.model.Medicine;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.utils.enums.Status;

@Service
public class MedicalRecordService {
    private final DiseaseRepository diseaseRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    MedicalRecordService(MedicalRecordRepository medicalRecordRepository, DiseaseRepository diseaseRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.diseaseRepository = diseaseRepository;
    }

    public Medicine insertMedicineDataDummy() {
        Medicine medicine = new Medicine();
        medicine.setName("Paracetamol");
        medicine.setDosage("500mg");
        medicine.setUnitPrice(100);
        medicine.setQuantity(10);

        return medicineRepository.save(medicine);
    }

    public Disease insertDiseaseDataDummy() {
        Disease disease = new Disease();
        disease.setName("Flu");
        return disease;
    }

    public MedicalRecordResponse createMedicalRecord(MedicalRecordRequest request, String result) {
        // input data dummyyy
        if (medicineRepository.count() == 0) {
            insertMedicineDataDummy();
        }

        if (diseaseRepository.count() == 0) {
            insertDiseaseDataDummy();
        }

        if (request.getAppointment().getStatus() != Status.RESERVED) {
            throw new IllegalArgumentException("Appointment status must be reserved.");
        }
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setSymptoms(request.getDiagnosis());
        medicalRecord.setResult(result);
        medicalRecord.setAppointment(request.getAppointment());
        medicalRecord.setDisease(request.getDisease());
        medicalRecord.setMedicine(request.getMedicine());

        medicalRecordRepository.save(medicalRecord);

        return new MedicalRecordResponse(
                medicalRecord.getId(),
                medicalRecord.getSymptoms(),
                medicalRecord.getResult());
    }
}
