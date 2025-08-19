package com.main.app.Services;

import com.main.app.Model.Person;
import com.main.app.Repositories.PersonRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Person registerUser(Person person) {
        if (personRepository.existsByUserName(person.getUserName())) {
            throw new IllegalArgumentException("Username already exists");
        }
        personRepository.save(person);
        return person;
    }

    public boolean existsByUsername(String username) {
        return personRepository.existsByUserName(username);
    }
    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }


    public Person findByUsername(String username) {

        return personRepository.findByUserName(username);

    }

   public List<Person> findAllUsers() {
        return personRepository.findAll();
    }


    public void deleteById(Long userId) {
        personRepository.deleteById(userId);

    }

    public Person save(Person person) {
        return personRepository.save(person);
    }
}