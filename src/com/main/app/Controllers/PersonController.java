package com.main.app.Controllers;

import com.main.app.Dto.PersonDto;
import com.main.app.Model.Person;
import com.main.app.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // You can change the base path if needed
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons/update")
    public ResponseEntity<?> updatePerson(@RequestBody PersonDto personDto) {
        try {
            // Retrieve the existing Person from the database
             Person person = personService.findPersonById(personDto.getId());

            if (person == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found with ID: " + personDto.getId());
            }


            person.setUserName(personDto.getUsername());
            person.setEmail(personDto.getEmail());
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            person.setBirthDate(personDto.getDateOfBirth());
            person.setPhone(personDto.getPhoneNumber());
      //dodaj ostala polja

            // Save the updated Person object
            Person updatedPerson = personService.save(person);

            return ResponseEntity.ok(updatedPerson); // Or a success message/DTO

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating person: " + e.getMessage());
        }
    }
}