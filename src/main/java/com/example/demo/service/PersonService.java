package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    // public UserWithPersonResponse getUserWithPersonByUsername(String username) {
    // return userRepository.findUserByUsername(username);
    // }

    public Person updatePerson(Integer id, Person personDetails) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Person existingPerson = person.get();
            existingPerson.setFirstName(personDetails.getFirstName());
            existingPerson.setLastName(personDetails.getLastName());
            existingPerson.setGender(personDetails.getGender());
            existingPerson.setPhoneNumber(personDetails.getPhoneNumber());
            existingPerson.setDateOfBirth(personDetails.getDateOfBirth());
            existingPerson.setAddress(personDetails.getAddress());
            return personRepository.save(existingPerson);
        } else {
            return null;
        }
    }

}
