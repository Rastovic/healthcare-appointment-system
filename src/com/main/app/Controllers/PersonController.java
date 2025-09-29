package com.main.app.Controllers;

import com.main.app.Dto.PersonDto;
import com.main.app.Model.Person;
import com.main.app.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    // CREATE a new person
    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) {
        try {
            Person person = personService.fromDto(personDto); // Convert DTO to entity
            Person savedPerson = personService.save(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating person: " + e.getMessage());
        }
    }

    // READ all persons
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.findAllUsers();
        return ResponseEntity.ok(persons);
    }

    // READ a single person by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {
        Person person = personService.findPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found with ID: " + id);
    }
    @GetMapping("/admins")
    public List<Person> getAdmins() {
        return personService.findPersonsByRole(1L);
    }
    @GetMapping("/doctors")
    public List<Person> getDoctors() {
        return personService.findPersonsByRole(2L);
    }
    @GetMapping("/patients")
    public List<Person> getPatients() {
        return personService.findPersonsByRole(3L);
    }
    // UPDATE a person
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        try {
            Person person = personService.findPersonById(id);
            if (person == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found with ID: " + id);
            }

            // Update fields
            person.setUserName(personDto.getUsername());
            person.setEmail(personDto.getEmail());
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            person.setBirthDate(personDto.getDateOfBirth());
            person.setPhone(personDto.getPhoneNumber());
            // Add any other fields as needed

            Person updatedPerson = personService.save(person);
            return ResponseEntity.ok(updatedPerson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating person: " + e.getMessage());
        }
    }

    // DELETE a person
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        try {
            Person person = personService.findPersonById(id);
            if (person == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found with ID: " + id);
            }
            personService.deleteById(id);
            return ResponseEntity.ok("Person deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting person: " + e.getMessage());
        }
    }
    // READ all persons with role = 2
    @GetMapping("/role/2")
    public ResponseEntity<List<Person>> getAllPersonsWithRole2() {
        try {
            List<Person> persons = personService.findPersonsByRole(2L);
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/role/3")
    public ResponseEntity<List<Person>> getAllPersonsWithRole3() {
        try {
            List<Person> persons = personService.findPersonsByRole(3L);
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Person>> searchPersons(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        try {
            List<Person> persons = personService.searchPersons(firstName, lastName, email);
            return ResponseEntity.ok(persons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
