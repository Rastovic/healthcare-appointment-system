package com.main.app.Controllers;

import com.main.app.Dto.AppointmentDto;
import com.main.app.Model.Appointment;
import com.main.app.Model.Person;
import com.main.app.Services.AppointmentService;
import com.main.app.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PersonService personService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDto>> getAppointments(Authentication authentication) {
        String userName  = SecurityContextHolder.getContext().getAuthentication().getName();
        Person doctor = personService.findByUsername(userName);
        List<AppointmentDto> appointments = appointmentService.getAppointmentsByDoctorId(doctor.getId());
        return new ResponseEntity<>(appointments, HttpStatus.OK);    }
}
