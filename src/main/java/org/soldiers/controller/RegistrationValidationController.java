package org.soldiers.controller;

import org.soldiers.model.User;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/registrationValidation")
public class RegistrationValidationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/checkUsername/{username}")
    public String checkUsername(@PathVariable String username) {
        User u = userRepository.findByUsername(username);
        if (u == null) {
            return "";
        }
        return "Istnieje już taki użytkownik w bazie";
    }

    @GetMapping("/checkEmail/{email}")
    public String checkEmail(@PathVariable String email) {
        User u = userRepository.findByEmail(email);
        if (u == null) {
            return "";
        }
        return "Istnieje już użytkownik o takim adresie e-mail";
    }
}
