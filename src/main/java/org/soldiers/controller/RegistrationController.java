package org.soldiers.controller;

import org.soldiers.model.Soldier;
import org.soldiers.model.User;
import org.soldiers.model.Role;
import org.soldiers.repository.SoldierRepository;
import org.soldiers.repository.UserRepository;
import org.soldiers.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SoldierRepository soldierRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Coś poszło nie tak";
        }
        try {
            Role role = roleRepository.findByRole("SOLDIER");
            Soldier soldier = soldierRepository.findById(user.getId()).get();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(role);
            soldier.setUser(user);
            System.out.println(role);
            System.out.println(user);
            System.out.println(soldier);
            userRepository.save(user);
            soldierRepository.save(soldier);
            return "Zarejestrowałeś/aś się";
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return "Coś poszło nie tak";
        }
    }
}
