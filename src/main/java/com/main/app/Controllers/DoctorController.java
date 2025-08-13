package com.main.app.Controllers;


import com.main.app.Dto.AppointmentDto;
import com.main.app.Dto.UserDto;
import com.main.app.Dto.DoctorDto;
import org.springframework.ui.Model;
import com.main.app.Model.Appointment;
import com.main.app.Model.Doctor;
import com.main.app.Model.User;
import com.main.app.Services.DoctorService;
import com.main.app.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import com.main.app.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
@PreAuthorize("hasRole('DOCTOR')") // Apply role restriction at the class level
public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;

 @Autowired
 private UserService userService;

 @Autowired
 private DoctorService doctorService; // Autowire DoctorService

    // Endpoint to get all appointments for the authenticated doctor
    @GetMapping("/all_appointments/{doctorId}")
    @PreAuthorize("#doctorId == authentication.principal.doctor.id") // Ensure doctorId matches authenticated doctor
    public String getAllAppointments(@PathVariable Long doctorId, Model model) {
        List<Appointment> appointments = appointmentService.findAllByDoctorId(doctorId);
        model.addAttribute("appointments", appointments);
        return "doctor/doctor_appointments";
    }






    @GetMapping("/appointments")
    public String viewAppointments(@RequestParam Long doctorId, Model model) { // This endpoint seems redundant with /all_appointments
        List<Appointment> appointments = appointmentService.findByDoctorId(doctorId);
        model.addAttribute("appointments", appointments);
        return "doctor/appointments";

    }


    @PostMapping("/update_appointment")
    public String updateAppointment(@RequestParam Long appointmentId, @RequestParam String status) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        appointment.setStatus(status);
         // Fetch the existing Doctor entity
         Optional<Doctor> doctorOptional = doctorService.findById(appointment.getDoctor().getId()); // Assuming doctorId is available or passed
        doctorOptional.ifPresent(appointment::setDoctor);
        appointmentService.updateAppointment(appointment);

        return "redirect:/doctor/appointments";
    }


    @GetMapping("/patient_info")
    public String viewPatientInfo(@RequestParam Long appointmentId, Model model) { // Consider access control to ensure doctor is assigned to this appointment
        Optional<Appointment> appointment = appointmentService.findById(appointmentId);

        model.addAttribute("appointment", convertToAppointmentDto(appointment.get())); // Pass DTO to template

        return "doctor/patient_info";

    }

     @GetMapping("/profile")
     public String viewDoctorProfile(Authentication auth, Model model) {
         User user = userService.findByUsername(auth.getName());
         if (user == null) {
             return "redirect:/login"; // Redirect to login if user not found (shouldn't happen with @PreAuthorize but good practice)
         }

         // Fetch the associated Doctor entity based on User ID
         Optional<Doctor> doctorOptional = doctorService.findById(user.getId()); // Assuming findByUserId method exists in DoctorService
         if (doctorOptional.isEmpty()) {
         // Handle case where user is a DOCTOR but no associated Doctor entity exists
         // This might require an initial setup flow for doctors
         return "redirect:/error"; // Example: redirect to an error or setup page
         }
         Doctor doctor = doctorOptional.get();
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
    // This should ideally be in a shared utility class or the UserService
    private UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setDateOfBirth(user.getDateOfBirth()); // Assuming DateOfBirth is in UserDto
        userDto.setPhoneNumber(user.getPhoneNumber()); // Assuming PhoneNumber is in UserDto
        userDto.setRole(user.getRole());
        return userDto;
    }

    // Helper method to convert Appointment entity to AppointmentDto
    // This should ideally be in a shared utility class or the AppointmentService
    private AppointmentDto convertToAppointmentDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
        appointmentDto.setStatus(appointment.getStatus());
        appointmentDto.setPatientName(appointment.getPatient() != null ? appointment.getPatient().getName() : "N/A");
        appointmentDto.setDoctorName(appointment.getDoctor() != null ? appointment.getDoctor().getName() : "N/A");
        return appointmentDto;
    }
}