package org.soldiers.controller;

import org.soldiers.model.User;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
public class AdminUsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/searchByUsername/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}
