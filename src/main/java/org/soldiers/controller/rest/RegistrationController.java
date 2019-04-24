package org.soldiers.controller.rest;

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
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private SoldierRepository soldierRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository, SoldierRepository soldierRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.soldierRepository = soldierRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/username/{username}")
    public User checkUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/email/{email}")
    public User checkEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }

    @PostMapping("/register")
    public Object register(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            Role role = roleRepository.findByRole("SOLDIER");
            Soldier soldier = soldierRepository.findById(user.getId()).get();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(role);
            soldier.setUser(user);
            return soldierRepository.save(soldier);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
