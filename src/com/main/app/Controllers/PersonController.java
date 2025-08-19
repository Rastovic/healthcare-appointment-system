package com.main.app.Controllers;

import com.main.app.Dto.PersonDto;
import com.main.app.Model.Person;
import com.main.app.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api") // You can change the base path if needed
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons/update")
    public ResponseEntity<?> updatePerson(@RequestBody PersonDto personDto) {
        try {
            // Retrieve the existing Person from the database
            Optional<Person> existingPersonOptional = personService.findById(personDto.getId());

            if (!existingPersonOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found with ID: " + personDto.getId());
            }

            Person existingPerson = existingPersonOptional.get();

            // Update the existing Person object with data from the PersonDto
            existingPerson.setUserName(personDto.getUserName());
            existingPerson.setEmail(personDto.getEmail());
            existingPerson.setFirstName(personDto.getFirstName());
            existingPerson.setLastName(personDto.getLastName());
            existingPerson.setBirthDate(personDto.getBirthDate());
            existingPerson.setPhoneNumber(personDto.getPhoneNumber());
            existingPerson.setMedicalHistory(personDto.getMedicalHistory());
            existingPerson.setInsuranceProvider(personDto.getInsuranceProvider());
            // Assuming password update is handled separately for security reasons,
            // or you would add password related fields to PersonDto and handle them here.
            // For simplicity, we are not updating password here.


            // Save the updated Person object
            Person updatedPerson = personService.save(existingPerson);

            return ResponseEntity.ok(updatedPerson); // Or a success message/DTO

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating person: " + e.getMessage());
        }
    }
}