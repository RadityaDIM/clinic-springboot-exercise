package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "tb_tr_meeting_appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "tb_m_patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "tb_m_doctor_id")
    private Doctor doctor;

    public Appointment(Integer id) {
        this.id = id;
    }
}
