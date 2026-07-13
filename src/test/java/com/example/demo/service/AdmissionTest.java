package com.example.demo.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.request.AdmissionRequest;
import com.example.demo.model.Room;
import com.example.demo.repository.AdmissionRepository;
import com.example.demo.repository.MedicalRecordRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.utils.enums.RoomClass;

import io.jsonwebtoken.lang.Assert;

@SpringBootTest
public class AdmissionTest {
    @Autowired
    AdmissionRepository admissionRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    AdmissionService admissionService;

    @Test
    public void testCreateAdmission() {
        Room room = new Room();
        room.setName("Dahlia");
        room.setPricePerDay(100000);
        room.setRoomClass(RoomClass.STANDARD);
        roomRepository.save(room);

        AdmissionRequest admissionRequest = new AdmissionRequest();
        admissionRequest.setAdmissionDate(LocalDateTime.now());
        admissionRequest.setMedicalRecordId(1);
        admissionRequest.setRoomId(1);

        Assert.notNull(admissionService.createAdmission(admissionRequest));

    }
}
