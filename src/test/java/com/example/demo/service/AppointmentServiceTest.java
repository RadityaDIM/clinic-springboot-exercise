package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.request.AppointmentRequest;
import com.example.demo.dto.response.AppointmentResponse;
import com.example.demo.dto.response.AppointmentWithPatientAndDoctor;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.model.Person;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.enums.Gender;
import com.example.demo.utils.enums.Status;

@SpringBootTest
public class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testBookAppointment() {
        AppointmentRequest request = new AppointmentRequest();

        User userForPatient = userRepository.findUserByUsername("username_test");

        Patient patient = new Patient();
        patient.setUser(userForPatient);
        patient.setBloodType("A");
        patient.setHeight(170);
        patient.setWeight(70);
        Patient savedPatient = patientRepository.save(patient);

        // buat data person dan user utk dokter dummy
        Person personForDoctor = new Person();
        personForDoctor.setFirstName("Test");
        personForDoctor.setLastName("Doctor");
        personForDoctor.setGender(Gender.MALE);
        personForDoctor.setPhoneNumber("0822222222");
        personForDoctor.setDateOfBirth("1985-05-05");
        personForDoctor.setAddress("Doctor Address");

        Role doctorRole = roleRepository.findRoleByName("ROLE_DOCTOR");
        User userForDoctor = new User();
        userForDoctor.setPerson(personForDoctor);
        userForDoctor.setUsername("testdoctor");
        userForDoctor.setEmail("doctor@example.com");
        userForDoctor.setPassword("password");
        userForDoctor.setRole(doctorRole);
        personForDoctor.setUser(userForDoctor);

        User savedUserForDoctor = userRepository.save(userForDoctor);

        // tambah data dokter
        Doctor doctor = new Doctor();
        doctor.setSpeciality("Cardiologist");
        doctor.setUser(savedUserForDoctor);
        Doctor savedDoctor = doctorRepository.save(doctor);

        request.setPatientId(savedPatient.getId());
        request.setDoctorId(savedDoctor.getId());
        request.setAppointmentDate(LocalDate.now());

        AppointmentResponse response = appointmentService.bookAppointment(request);

        Assertions.assertNotNull(response);
    }

    @Test
    public void manageAppointment() {
        int id = 1;
        Status status = Status.RESERVED;
        try {
            appointmentService.manageAppointment(id, status);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void displayAllAppointmentWithPatientAndDoctor() {
        List<AppointmentWithPatientAndDoctor> response = appointmentService.displayAllAppointmentWithPatientAndDoctor();

        Assertions.assertNotNull(response);
    }
}
