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
        Patient patient = patientRepository.findPatientById(request.getPatientId());
        Doctor doctor = doctorRepository.findDoctorById(request.getDoctorId());

        if (patient == null) {
            throw new IllegalArgumentException("Data pasien tidak ditemukan.");
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Data dokter tidak ditemukan");
        }
        if (request.getAppointmentDate() == null) {
            throw new IllegalArgumentException("Tanggal appointment harus disediakan.");
        }
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
