package org.soldiers.controller.rest;

import org.soldiers.model.User;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/settings")
public class SettingsController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SettingsController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{id}/{oldPassword}")
    public User getUserByPassword(@PathVariable Long id, @PathVariable String oldPassword) {
        User u1 = userRepository.findById(id).get();
        if (passwordEncoder.matches(oldPassword, u1.getPassword())) {
            return u1;
        }
        return null;
    }

    @PutMapping("/{id}/{newPassword}")
    public User updateUserPassword(@PathVariable Long id, @PathVariable String newPassword) {
        try {
            User u1 = userRepository.findById(id).get();
            u1.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(u1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public Object updateUser(@PathVariable Long id, User user, HttpServletResponse httpServletResponse) {
        try {
            User u1 = userRepository.findById(id).get();
            User u2 = userRepository.findByUsername(user.getUsername());
            User u3 = userRepository.findByEmail(user.getEmail());
            if (u1 != u2 && u2 != null) {
                return "Już istnieje użytkownik o takiej nazwie";
            }
            if (u1 != u3 && u3 != null) {
                return "Już istnieje użytkonwik o takim adresie email";
            }
            u1.setEmail(user.getEmail());
            u1.setUsername(user.getUsername());
            return userRepository.save(u1);
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }
}
