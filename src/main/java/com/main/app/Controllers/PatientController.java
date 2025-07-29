package com.main.app.Controllers;

import com.main.app.Model.Appointment;
import com.main.app.Model.Doctor;
import org.springframework.ui.Model;
import com.main.app.Model.Patient;
import com.main.app.Model.User;
import com.main.app.Services.AppointmentService;
import com.main.app.Services.DoctorService;
import com.main.app.Services.PatientService;
import com.main.app.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;




    @GetMapping("/profile")

    public String viewProfile(Authentication auth, Model model) {

        User user = userService.findByUsername(auth.getName());

        Patient patient = patientService.findByUserId(user.getId());

        model.addAttribute("patient", patient);

        return "patient/profile";

    }





    @PostMapping("/profile")

    public String updateProfile(@ModelAttribute Patient patient, Authentication auth) {

        User user = userService.findByUsername(auth.getName());

        patient.setUser(user);

        patientService.savePatient(patient);

        return "redirect:/patient/profile";

    }





    @GetMapping("/search_doctors")

    public String searchDoctors(@RequestParam String specialty, Model model) {

        List<Doctor> doctors = doctorService.findBySpecialty(specialty); // VULNERABLE: No sanitization (A03)

        model.addAttribute("doctors", doctors);

        return "patient/search_doctors";

    }





    @PostMapping("/book_appointment")

    public String bookAppointment(@RequestParam Long doctorId, @RequestParam String time, Authentication auth) {

        User user = userService.findByUsername(auth.getName());

        Patient patient = patientService.findByUserId(user.getId());

        Appointment appointment = new Appointment();

        appointment.setPatient(patient);

        appointment.setDoctor(new Doctor(doctorId));

        appointment.setAppointmentTime(java.time.LocalDateTime.parse(time));

        appointment.setStatus("SCHEDULED");

        appointmentService.bookAppointment(appointment);

        return "redirect:/patient/appointment_history";

    }





    @GetMapping("/appointment_history")

    public String appointmentHistory(Authentication auth, Model model) {

        User user = userService.findByUsername(auth.getName());

        Patient patient = patientService.findByUserId(user.getId());

        List<Appointment> appointments = appointmentService.findByPatientId(patient.getId());

        model.addAttribute("appointments", appointments);

        return "patient/appointment_history";

    }





    @PostMapping("/cancel_appointment")

    public String cancelAppointment(@RequestParam Long appointmentId) {

        // VULNERABLE: No ownership check (A01)

        Appointment appointment = new Appointment();

        appointment.setId(appointmentId);

        appointment.setStatus("CANCELED");

        appointmentService.updateAppointment(appointment);

        return "redirect:/patient/appointment_history";

    }

}
