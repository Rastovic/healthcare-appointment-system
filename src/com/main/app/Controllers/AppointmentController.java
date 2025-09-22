package com.main.app.Controllers;

import com.main.app.Dto.AppointmentDto;
import com.main.app.Services.AppointmentService;
import com.main.app.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Patient’s appointments
    @GetMapping("/patient/{personId}")
    public List<AppointmentDto> getAppointmentsForPatient(@PathVariable Long personId) {
        return appointmentService.getAppointmentsByPatientId(personId);
    }

    // Doctor’s appointments
    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDto> getAppointmentsForDoctor(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }

    // Create new appointment
    @PostMapping("/create")
    public AppointmentDto createAppointment(@RequestBody AppointmentDto appointment) {
        return appointmentService.createAppointment(appointment);
    }

    // Update appointment
    @PutMapping("/{id}")
    public AppointmentDto updateAppointment(@PathVariable Long id, @RequestBody AppointmentDto updated) {
        return appointmentService.updateAppointment(id, updated);
    }

    // Delete appointment
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}