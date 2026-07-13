package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Admission;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {

}
