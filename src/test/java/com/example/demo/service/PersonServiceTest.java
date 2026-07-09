package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.model.Person;
import com.example.demo.utils.enums.Gender;

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    void testSavePerson_Success() {

        // Arrange
        Person personInput = new Person();
        personInput.setFirstName("Raditya");
        personInput.setLastName("Dimas");
        personInput.setGender(Gender.MALE);
        personInput.setPhoneNumber("08123456789");
        personInput.setDateOfBirth("1990-01-01");
        personInput.setAddress("Jl. Sudirman No. 1, Jakarta");

        // Act
        Person result = personService.savePerson(personInput);

        // Assert
        assertNotNull(result.getId(), "ID seharusnya sudah ter-generate otomatis oleh database");
        assertTrue(result.getId() > 0, "ID harus berupa angka positif");

        assertEquals("Raditya", result.getFirstName());
        assertEquals("Dimas", result.getLastName());
        assertEquals(Gender.MALE, result.getGender());
        assertEquals("08123456789", result.getPhoneNumber());
        assertEquals("1990-01-01", result.getDateOfBirth());
        assertEquals("Jl. Sudirman No. 1, Jakarta", result.getAddress());

    }
}
