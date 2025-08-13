package com.main.app.Controllers;

import com.main.app.Dto.AppointmentDto;
import com.main.app.Model.Appointment;
import com.main.app.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/appointments")

public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;





    @GetMapping
    public List<Appointment> getAllAppointments() {

        // VULNERABLE: Exposes all appointments (A01)

        return appointmentService.findAll();

    }





    @GetMapping("/{id}")

    public Appointment getAppointment(@PathVariable Long id) {

        // VULNERABLE: No access control (A01)

        return appointmentService.findById(id).get();

    }

}
