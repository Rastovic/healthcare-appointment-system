package com.main.app.Services;

import com.main.app.Dto.PersonDto;
import com.main.app.Model.Person;
import com.main.app.Repositories.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> searchPersons(String firstName, String lastName, String email) {
        StringBuilder sql = new StringBuilder("SELECT * FROM person p WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (StringUtils.hasText(firstName)) {
            sql.append(" AND LOWER(p.first_name) LIKE LOWER(?) ");
            params.add("%" + firstName + "%");
        }
        if (StringUtils.hasText(lastName)) {
            sql.append(" AND LOWER(p.last_name) LIKE LOWER(?) ");
            params.add("%" + lastName + "%");
        }
        if (StringUtils.hasText(email)) {
            sql.append(" AND LOWER(p.email) LIKE LOWER(?) ");
            params.add("%" + email + "%");
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Person.class);

        // bind parameters safely
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i)); // JDBC parameters are 1-indexed
        }

        return query.getResultList();
    }


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

    public Person findPersonById(Long personId) {
        return personRepository.findById(personId).orElse(null);
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

    public List<Person> findPersonsByRole(Long roleId) {
        return personRepository.findByRoleId(roleId);
    }

    public Person fromDto(PersonDto dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setUserName(dto.getUsername());
        person.setEmail(dto.getEmail());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setBirthDate(dto.getDateOfBirth());
        person.setPhone(dto.getPhoneNumber());
        // add other fields
        return person;
    }

}