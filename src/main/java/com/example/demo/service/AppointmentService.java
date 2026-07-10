package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.AppointmentRequest;
import com.example.demo.dto.response.AppointmentResponse;
import com.example.demo.dto.response.AppointmentWithPatientAndDoctor;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.utils.enums.Status;
import com.example.demo.repository.DoctorRepository;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public AppointmentResponse bookAppointment(AppointmentRequest request) {
        if (request.getPatient() == null || request.getPatient().getId() == null) {
            throw new IllegalArgumentException("Patient ID harus disediakan.");
        }
        if (request.getDoctor() == null || request.getDoctor().getId() == null) {
            throw new IllegalArgumentException("Doctor ID harus disediakan.");
        }
        if (request.getAppointmentDate() == null) {
            throw new IllegalArgumentException("Tanggal appointment harus disediakan.");
        }

        Patient patient = patientRepository.findById(request.getPatient().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Patient tidak ditemukan dengan ID: " + request.getPatient().getId()));

        Doctor doctor = doctorRepository.findById(request.getDoctor().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Doctor tidak ditemukan dengan ID: " + request.getDoctor().getId()));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus(Status.PENDING); // default emg set di pending

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return new AppointmentResponse(
                savedAppointment.getId(),
                savedAppointment.getAppointmentDate(),
                savedAppointment.getStatus());
    }

    public AppointmentResponse manageAppointment(Integer id, Status status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + id));

        appointment.setStatus(status);
        appointmentRepository.save(appointment);

        return new AppointmentResponse();
    }

    public List<AppointmentWithPatientAndDoctor> displayAllAppointmentWithPatientAndDoctor() {
        return appointmentRepository.getAllAppointmentWithPatientAndDoctor();
    }
}
