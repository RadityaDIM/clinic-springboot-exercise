package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {

    @Query("SELECT d FROM Disease d WHERE d.id = :id")
    public Disease findDiseaseById(@Param("id") Integer id);
}
