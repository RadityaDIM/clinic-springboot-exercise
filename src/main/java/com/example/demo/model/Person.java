package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.utils.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // untuk menandakan representasi tabel database
@Data // untuk setter getter
@AllArgsConstructor // bisa noArgsCons untuk constructor
@NoArgsConstructor
@Table(name = "tb_m_person") // untuk naming di database semisal BERBEDA dengan nama variabel (camel case)
public class Person {
    // Harus ada primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // untuk auto increment
    private Integer id;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;
    private String dateOfBirth;
    private String address;

    public Person(Integer id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private User user;
}
