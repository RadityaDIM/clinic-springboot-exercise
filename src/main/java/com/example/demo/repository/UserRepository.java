package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.response.UserWithPersonResponse;
import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findUserByUsername(String username);

    // Param
    // 3 options:
    // 1. with :username -> define with @Param("username") String username
    // 2. with ?, but must be single params
    // 3. with ?1, ?2, ?3, but must be sequential with parameters

    @Query("SELECT new com.example.demo.dto.response.UserWithPersonResponse(u.id, u.username, u.email, p.firstName, p.address) FROM User u JOIN u.person p")
    public List<UserWithPersonResponse> findUserIncludePerson();

    // @Query("SELECT new com.example.demo.dto.response.UserWithPersonResponse(u.id,
    // u.username, u.email, p.firstName, p.address) FROM User u JOIN u.person p
    // WHERE u.username = :username")
    // public UserWithPersonResponse findUserByUsername(@Param("username") String
    // username);

}
