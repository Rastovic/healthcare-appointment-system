package com.main.app.Controllers;

import ch.qos.logback.core.boolex.Matcher;
import com.main.app.Config.SecurityConfig;
import com.main.app.Model.User;
import com.main.app.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Status;
import org.apache.logging.log4j.status.StatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityConfig securityConfig;



    @GetMapping("/login")
    public String login() {

        return "login";

    }



    @GetMapping("/register")
    public String register() {

        return "register";

    }


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestParam String username,
                                                            @RequestParam String password,
                                                            @RequestParam String email,
                                                            @RequestParam String role) {
        Map<String, String> response = new HashMap<>();

        User user = new User();
        user.setUsername(username);
        user.setPassword(securityConfig.passwordEncoder().encode(password));
        user.setEmail(email);
        user.setRole("PATIENT");

        try {
            userService.registerUser(user);
            response.put("status", "success");
            response.put("message", "Registration successful! Please log in.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Registration failed. Try a different username or email.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/doLogin")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {

        User user = userService.findByUsername(username);

        if (user != null && securityConfig.passwordEncoder().matches(password, user.getPassword())) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

            // ✅ Add this line:
            session.setAttribute("user", user);

            return "redirect:/profile";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
            return "redirect:/login";
        }
    }





    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        System.out.println("Accessing profile");
        ModelAndView mav = new ModelAndView("profile");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("No user in session, redirecting to login");
            return new ModelAndView("redirect:/login");
        }
        mav.addObject("user", user);
        if ("ADMIN".equals(user.getRole())) {
            mav.addObject("allUsers", userService.findAllUsers());
        }
        return mav;
    }

}
