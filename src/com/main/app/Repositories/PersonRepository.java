package com.main.app.Repositories;


import com.main.app.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEMail(String email);

    boolean existsByEmail(String email);}
