package com.main.app.Controllers;

import com.main.app.Model.Person;
import com.main.app.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileViewController {

    private final PersonService personService;

    @Autowired
    public ProfileViewController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public String getProfilePage(@PathVariable Long id, Model model) {
        Person person = personService.findPersonById(id);
        if (person == null) {
            return "redirect:/appointments/appointments"; // fallback
        }
        model.addAttribute("person", person);
        model.addAttribute("personId", person.getId()); // add this line

        return "profile/profile-page"; // Thymeleaf template
    }
}
