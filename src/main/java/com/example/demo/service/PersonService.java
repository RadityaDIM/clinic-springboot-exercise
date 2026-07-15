package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.repository.PersonRepository;
import com.example.demo.dto.response.UserWithPersonResponse;
import com.example.demo.model.Person;

@Service
public class PersonService {
    private final UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;

    PersonService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Integer getLatestId() {
        return personRepository.findTopByOrderByIdDesc().getId();
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public List<UserWithPersonResponse> getUserWithPerson() {
        return userRepository.findUserIncludePerson();
    }

    public Person updatePerson(@NonNull Integer id, Person personDetails) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Update gagal: data person tidak ditemukan"));
        if (personDetails != null) {
            if (personDetails.getFirstName() != null) {
                existingPerson.setFirstName(personDetails.getFirstName());
            }
            if (personDetails.getLastName() != null) {
                existingPerson.setLastName(personDetails.getLastName());
            }
            if (personDetails.getGender() != null) {
                existingPerson.setGender(personDetails.getGender());
            }
            if (personDetails.getPhoneNumber() != null) {
                existingPerson.setPhoneNumber(personDetails.getPhoneNumber());
            }
            if (personDetails.getDateOfBirth() != null) {
                existingPerson.setDateOfBirth(personDetails.getDateOfBirth());
            }
            if (personDetails.getAddress() != null) {
                existingPerson.setAddress(personDetails.getAddress());
            }
        }
        return personRepository.save(existingPerson);
    }

}
