package com.main.app.Repositories;


import com.main.app.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUserName(String userName);

    Optional<Person> findById(Long personId);

    boolean existsByUserName(String userName);

    List<Person> findByRoleId(Long roleId);

    boolean existsByEmail(String email);}
