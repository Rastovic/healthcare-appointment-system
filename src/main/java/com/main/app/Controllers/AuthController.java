package com.main.app.Controllers;

import ch.qos.logback.core.boolex.Matcher;
import com.main.app.Config.SecurityConfig;
import com.main.app.Model.User;
import com.main.app.Dto.UserDto;
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

import java.time.LocalDate;
import java.util.*;

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
                                                            @RequestParam String fullName,
                                                            @RequestParam String phoneNumber,
                                                            @RequestParam String role,
                                                            @RequestParam LocalDate dateOfBirth) { // Removed unused role parameter
        Map<String, String> response = new HashMap<>();

        User user = new User();
        user.setUsername(username);
        user.setPassword(securityConfig.passwordEncoder().encode(password));
        user.setEmail(email);
        user.setRole("PATIENT");
        user.setDateOfBirth(dateOfBirth);
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);

        try {
            User registeredUser = userService.registerUser(user);
            Map<String, Object> successResponse = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Registration successful! Please log in.");
 successResponse.put("user", convertToDto(registeredUser));
 return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
        } catch (Exception e) {
            response.put("status", "error");
 response.put("message", "Registration failed. Try a different username or email. Error: " + e.getMessage());
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
        ModelAndView mav = new ModelAndView("profile");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/login");
        }

        // Convert User entity to UserDto before adding to model
        mav.addObject("user", convertToDto(user));

        if ("ADMIN".equals(user.getRole())) {
            // Convert list of User entities to list of UserDtos
            List<User> allUsers = userService.findAllUsers();
            List<UserDto> allUserDtos = allUsers.stream()
                    .map(this::convertToDto)
                    .toList();
            mav.addObject("allUsers", allUserDtos);
        }
        return mav;
    }

    // Helper method to convert User entity to UserDto
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());
        return userDto;
    }

}
