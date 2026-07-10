package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_tr_medical_record")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String symptoms;
    private String result;

    @ManyToOne
    @JoinColumn(name = "tb_m_medicine_id")
    private Medicine medicine;

    @OneToOne
    @JoinColumn(name = "tb_tr_appointment_id")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "tb_m_disease_id")
    private Disease disease;

}
