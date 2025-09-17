package com.main.app.Controllers;

import com.main.app.Dto.AppointmentDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// For HTML pages
@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Thymeleaf template login.html
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Thymeleaf template register.html
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/login";
    }


    @GetMapping("/forbidden")
    public String forbidden() {
        return "forbidden";
    }
}

