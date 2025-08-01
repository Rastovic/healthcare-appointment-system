package com.main.app.Controllers;


import org.springframework.ui.Model;
import com.main.app.Model.Appointment;
import com.main.app.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")

public class DoctorController {

    @Autowired
    private AppointmentService appointmentService;





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

}