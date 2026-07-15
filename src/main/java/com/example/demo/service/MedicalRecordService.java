package com.example.demo.service;

import com.example.demo.repository.DiseaseRepository;
import com.example.demo.repository.MedicalRecordRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.MedicalRecordRequest;
import com.example.demo.dto.request.MedicineRequest;
import com.example.demo.dto.response.MedicalRecordResponse;
import com.example.demo.model.Appointment;
import com.example.demo.model.DetailTransaction;
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
        medicine.setUnitPrice(1000);
        medicine.setQuantity(900);

        return medicineRepository.save(medicine);
    }

    public Disease insertDiseaseDataDummy() {
        Disease disease = new Disease();
        disease.setName("Flu");

        return diseaseRepository.save(disease);
    }

    @Transactional
    public MedicalRecordResponse createMedicalRecord(MedicalRecordRequest request, String result) {
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment dengan ID tersebut tidak ditemukan"));
        ;
        Disease disease = diseaseRepository.findDiseaseById(request.getDiseaseId());

        // // input data dummyyy
        // if (medicineRepository.count() == 0) {
        // insertMedicineDataDummy();
        // }

        // if (diseaseRepository.count() == 0) {
        // insertDiseaseDataDummy();
        // }

        if (appointment.getStatus() != Status.RESERVED) {
            throw new IllegalArgumentException("Appointment status must be reserved.");
        }
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setSymptoms(request.getDiagnosis());
        medicalRecord.setResult(result);
        medicalRecord.setAppointment(appointment);
        medicalRecord.setDisease(disease);

        if (request.getMedicine() != null && !request.getMedicine().isEmpty()) {

            List<DetailTransaction> detailTransactions = new ArrayList<>();

            for (MedicineRequest medicineRequest : request.getMedicine()) {
                Medicine medicine = medicineRepository.findByName(medicineRequest.getName())
                        .orElseThrow(() -> new RuntimeException(
                                "Obat " + medicineRequest.getName() + " tidak ditemukan di database"));

                if (medicine.getQuantity() < medicineRequest.getQuantity()) {
                    throw new RuntimeException("Stok obat " + medicineRequest.getName() + " tidak mencukupi");
                } else {
                    medicine.setQuantity(medicine.getQuantity() - medicineRequest.getQuantity());
                }

                DetailTransaction detail = new DetailTransaction();
                detail.setDate(LocalDate.now());
                detail.setMedicine(medicine);
                detail.setMedicineQuantity(medicineRequest.getQuantity());
                detail.setMedicalRecord(medicalRecord);
                detail.setDosagingNotes("-");

                detailTransactions.add(detail);
            }
            medicalRecord.setDetailTransactions(detailTransactions);
        }
        medicalRecordRepository.save(medicalRecord);
        return new MedicalRecordResponse(
                medicalRecord.getId(),
                medicalRecord.getSymptoms(),
                medicalRecord.getResult());
    }
}
