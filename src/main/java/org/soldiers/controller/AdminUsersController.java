package org.soldiers.controller;

import org.soldiers.model.User;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
public class AdminUsersController {
    private UserRepository userRepository;

    @Autowired
    public AdminUsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/searchByUsername/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,  User user) {
        User u1 = userRepository.findById(id).get();
        u1.setRole(user.getRole());
        u1.setEnabled(user.getEnabled());
        return userRepository.save(u1);
    }
}
