package com.main.app.Repositories;


import com.main.app.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUserName(String userName);

    Optional<Person> findById(Long personId);

    boolean existsByUserName(String userName);


    boolean existsByEmail(String email);}
