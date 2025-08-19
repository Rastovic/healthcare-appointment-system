package com.main.app.Controllers;


import jakarta.servlet.http.HttpSession;
import com.main.app.Config.SecurityConfig;
import com.main.app.Model.Person;
import com.main.app.Model.Role;
import com.main.app.Services.PersonService;
import com.main.app.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // Keep ResponseEntity import if you need it elsewhere or for other methods
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AuthController {

    @Autowired
    private PersonService personService;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam String username,
                                          @RequestParam String password,
                                          @RequestParam String email,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName,
                                          @RequestParam String role,
                                          @RequestParam LocalDate dateOfBirth) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Check if username or email already exists
            if (personService.existsByUsername(username)) {
                response.put("status", "error");
                response.put("message", "Username already taken");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if (personService.existsByEmail(email)) {
                response.put("status", "error");
                response.put("message", "Email already registered");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            // Find role from DB
            Role roleEntity = roleService.findByRoleName(role.toUpperCase());
            if (roleEntity == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role doesn't exist.");
            }

            // Build person object
            Person person = new Person();
            person.setUserName(username);
            person.setPasswordHash(securityConfig.passwordEncoder().encode(password));
            person.setEmail(email);
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setBirthDate(dateOfBirth);
            person.setRoleId(roleEntity.getId());

            // Save person
            Person savedPerson = personService.save(person);

            response.put("status", "success");
            response.put("message", "User registered successfully");
            response.put("userId", savedPerson.getId());
            response.put("roleId", savedPerson.getRoleId());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Registration failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }






    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        ModelAndView mav = new ModelAndView("profile");
        Person user = (Person) session.getAttribute("user");
            return new ModelAndView("redirect:/login");
        }
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/forbidden")
    public String forbidden() {
        return "forbidden"; // point this to a Thymeleaf/HTML template
    }
}
