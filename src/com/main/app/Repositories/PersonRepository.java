package com.main.app.Repositories;


import com.main.app.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUserName(String userName);
    boolean existsByUserName(String userName);


    boolean existsByEmail(String email);}
