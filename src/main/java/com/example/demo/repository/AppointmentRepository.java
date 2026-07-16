package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.response.AppointmentWithPatientAndDoctor;
import com.example.demo.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("SELECT new com.example.demo.dto.response.AppointmentWithPatientAndDoctor(a.appointmentDate, CONCAT(pp.firstName, ' ', pp.lastName), CONCAT(dp.firstName, ' ', dp.lastName), CAST(a.status as string)) FROM Appointment a JOIN a.patient patient JOIN patient.user patientUser JOIN patientUser.person pp JOIN a.doctor doctor JOIN doctor.user doctorUser JOIN doctorUser.person dp")
    public List<AppointmentWithPatientAndDoctor> getAllAppointmentWithPatientAndDoctor();

    @Query("SELECT new com.example.demo.dto.response.AppointmentWithPatientAndDoctor(a.appointmentDate, CONCAT(pp.firstName, ' ', pp.lastName), CONCAT(dp.firstName, ' ', dp.lastName), CAST(a.status as string)) FROM Appointment a JOIN a.patient patient JOIN patient.user patientUser JOIN patientUser.person pp JOIN a.doctor doctor JOIN doctor.user doctorUser JOIN doctorUser.person dp WHERE patient.id = :patientId")
    public List<AppointmentWithPatientAndDoctor> getAppointmentByPatientId(@Param("patientId") Integer patientId);

    @Query("SELECT a FROM Appointment a WHERE a.id = :id")
    public Appointment findAppointmentById(@Param("id") Integer id);

}
