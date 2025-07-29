package com.main.app.Controllers;

import com.main.app.Model.User;
import com.main.app.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;



    @GetMapping("/login")
    public String login() {

        return "login";

    }



    @GetMapping("/register")
    public String register() {

        return "register";

    }


    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password,

                               @RequestParam String email, @RequestParam String role) {

        User user = new User();

        user.setUsername(username);

        user.setPassword(password); // VULNERABLE: No input sanitization (A03)

        user.setEmail(email);

        user.setRole(role);

        userService.registerUser(user);

        return "redirect:/login";

    }

}
