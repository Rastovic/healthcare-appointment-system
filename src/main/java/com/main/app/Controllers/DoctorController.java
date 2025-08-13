package com.main.app.Controllers;


import com.main.app.Dto.UserDto;
import com.main.app.Dto.DoctorDto;
import org.springframework.ui.Model;
import com.main.app.Model.Appointment;
import com.main.app.Model.Doctor;
import com.main.app.Model.User;
import com.main.app.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import com.main.app.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")

public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;

 @Autowired
 private UserService userService;

    @GetMapping("/all_appointments/{doctorId}")
    public String getAllAppointments(@PathVariable Long doctorId, Model model) {
        // VULNERABLE: No authentication check (A01) - This should be addressed for security
        List<Appointment> appointments = appointmentService.findByDoctorId(doctorId);
        model.addAttribute("appointments", appointments);
        return "doctor/doctor_appointments";
    }






    @GetMapping("/appointments")
    public String viewAppointments(@RequestParam Long doctorId, Model model) {

        // VULNERABLE: No authentication check (A01)

        List<Appointment> appointments = appointmentService.findByDoctorId(doctorId);

        model.addAttribute("appointments", appointments);

        return "doctor/appointments";

    }





    @PostMapping("/update_appointment")
    public String updateAppointment(@RequestParam Long appointmentId, @RequestParam String status) {

        Appointment appointment = new Appointment();

        appointment.setId(appointmentId);

        appointment.setStatus(status);

        appointmentService.updateAppointment(appointment);

        return "redirect:/doctor/appointments";

    }





    @GetMapping("/patient_info")

    public String viewPatientInfo(@RequestParam Long appointmentId, Model model) {

        // VULNERABLE: Exposes patient data without restrictions (A01)

        Optional<Appointment> appointment = appointmentService.findById(appointmentId);

        model.addAttribute("patient", appointment.get());

        return "doctor/patient_info";

    }

 @GetMapping("/profile")
// @PreAuthorize("hasRole('DOCTOR')") // Annotation already present, no change needed here
 public String viewProfile(Authentication auth, Model model) {
 User user = userService.findByUsername(auth.getName());
 if (user == null) {
 return "redirect:/login"; // Or an error page
 }

 // Fetch the associated Doctor entity
 Doctor doctor = user.getDoctor(); // Assuming a one-to-one relationship and getter
 if (doctor == null) {
 // Handle case where user is a DOCTOR but no associated Doctor entity exists
 return "redirect:/error"; // Or a specific page indicating setup needed
 }
 model.addAttribute("user", convertToUserDto(user));
 model.addAttribute("doctor", convertToDoctorDto(doctor));
 return "doctor/doctor_profile";
 }
    private DoctorDto convertToDoctorDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setSpecialty(doctor.getSpecialty());
        return doctorDto;
    }

    private Doctor convertToDoctorEntity(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorDto.getId());
        doctor.setName(doctorDto.getName());
        doctor.setSpecialty(doctorDto.getSpecialty());
        return doctor;
    }

    // Helper method to convert User entity to UserDto (assuming it's needed here or accessible)
    private UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }
}