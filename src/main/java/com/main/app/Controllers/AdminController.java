package com.main.app.Controllers;

import com.main.app.Dto.AuditLogDto;
import com.main.app.Dto.UserDto;
import com.main.app.Model.AuditLog;
import com.main.app.Model.User;
import com.main.app.Services.AppointmentService;
import com.main.app.Services.AuditLogService;
import com.main.app.Services.PatientService;
import com.main.app.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;




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

        List<UserDto> userDtos = userService.findAllUsers().stream().map(this::convertToUserDto).collect(Collectors.toList());
        model.addAttribute("users", userDtos);

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
