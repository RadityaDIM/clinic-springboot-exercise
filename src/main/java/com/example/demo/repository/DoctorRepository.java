package com.example.demo.repository;

import javax.print.Doc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    public Doctor findDoctorById(Integer id);
}
