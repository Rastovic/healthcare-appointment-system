package com.main.app.Controllers;

import com.main.app.Dto.AppointmentDto;
import com.main.app.Model.Person;
import com.main.app.Services.AppointmentService;
import com.main.app.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/appointments")
public class AppointmentViewController {

    private final AppointmentService appointmentService;

    @Autowired
    private PersonService personService;

    @Autowired
    public AppointmentViewController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // List of appointments
    @GetMapping
    public String getAppointmentsPage(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());

        return "doctor/doctor_appointments"; // your list template
    }
    @GetMapping("/create")
    public String getCreateAppointmentPage(@RequestParam(value = "personId", required = false) Long personId, Model model) {
        Person currentPerson = personService.findPersonById(personId);
        if (currentPerson == null) {
            return "redirect:/login"; // Redirect to login if no user is authenticated
        }
        model.addAttribute("person", currentPerson);
        model.addAttribute("appointment", new AppointmentDto()); // Empty DTO for the form
        return "appointment/create-appointment"; // Path to create-appointment.html
    }

    // Single appointment detail
    @GetMapping("/{id}")
    public String getAppointmentDetail(@PathVariable Long id, Model model) {
        AppointmentDto appointment = appointmentService.getAppointmentById(id);
        if (appointment == null) {
            return "redirect:/appointments"; // back to list
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("personId",appointment.getDoctorId());
        return "appointment/appointment-detail"; // your detail template
    }
}

