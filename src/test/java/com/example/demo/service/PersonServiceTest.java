package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.RegisterResponse;
import com.example.demo.dto.response.UserWithPersonResponse;
import com.example.demo.model.Person;
import com.example.demo.utils.enums.Gender;

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Autowired
    private AuthService authService;

    @Test
    void testSavePerson_Success() {

        // Arrange
        Person personInput = new Person();
        personInput.setFirstName("Raditya");
        personInput.setLastName("Dimas");
        personInput.setGender(Gender.MALE);
        personInput.setPhoneNumber("08123456789");
        personInput.setDateOfBirth(LocalDate.of(2000, 9, 15));
        personInput.setAddress("Jl. Sudirman No. 1, Jakarta");

        // Act
        Person result = personService.savePerson(personInput);

        // Assert

        assertEquals("Raditya", result.getFirstName());
        assertEquals("Dimas", result.getLastName());
        assertEquals(Gender.MALE, result.getGender());
        assertEquals("08123456789", result.getPhoneNumber());
        assertEquals("1990-01-01", result.getDateOfBirth());
        assertEquals("Jl. Sudirman No. 1, Jakarta", result.getAddress());

    }

    @Test
    void testGetUserWithPerson() {
        List<UserWithPersonResponse> userPersons = personService.getUserWithPerson();
        Assertions.assertNotNull(userPersons);
    }

    // @Test
    // void testGetUserWithPersonByUsername() {
    // UserWithPersonResponse userPerson =
    // personService.getUserWithPersonByUsername();
    // Assertions.assertNotNull(userPersons);
    // }

    @Test
    void testRegisterUser() {
        RegisterRequest request = new RegisterRequest(
                "username_test",
                "usertest@email.com",
                "password123",
                "First",
                "Last");

        RegisterResponse response = authService.register(request);

        Assertions.assertNotNull(response);

    }

    @Test
    void testLoginUser() {
        LoginRequest request = new LoginRequest(
                "username_test",
                "password123");

        LoginResponse response = authService.login(request);

        Assertions.assertNotNull(response);

    }

    @Test
    void testUpdateUser() {
        Person person = new Person();
        person.setFirstName("Raditya");
        person.setLastName("Dimas");
        person.setGender(Gender.MALE);
        person.setPhoneNumber("08123456789");
        person.setDateOfBirth(LocalDate.of(2002, 10, 20));
        person.setAddress("Jl. Sudirman No. 1, Jakarta");
        Person result = personService.updatePerson(personService.getLatestId(), person);
        assertNotNull(result);
    }
}
