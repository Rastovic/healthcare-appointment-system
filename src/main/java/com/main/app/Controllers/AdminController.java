package com.main.app.Controllers;

import com.main.app.Services.AppointmentService;
import com.main.app.Services.AuditLogService;
import com.main.app.Services.PatientService;
import com.main.app.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;





@Controller
@RequestMapping("/admin")

public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AuditLogService auditLogService;





    @GetMapping("/panel")

    public String adminPanel() {

        return "admin/panel";

    }





    @GetMapping("/manage_users")

    public String manageUsers(Model model) {

        model.addAttribute("users", userService.findAll());

        return "admin/manage_users";

    }





    @PostMapping("/delete_user")

    public String deleteUser(@RequestParam Long userId) {

        userService.deleteById(userId);

        auditLogService.logAction("User deleted: " + userId, "admin");

        return "redirect:/admin/manage_users";

    }





    @GetMapping("/audit_logs")

    public String viewAuditLogs(Model model) {

        model.addAttribute("logs", auditLogService.findAll());

        return "admin/audit_logs";

    }

}
