package org.soldiers.controller.rest;

import org.soldiers.model.User;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin/users")
public class AdminUsersController {
    private UserRepository userRepository;

    @Autowired
    public AdminUsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, User user, HttpServletResponse httpServletResponse) {
        try {
            User u1 = userRepository.findById(id).get();
            u1.setRole(user.getRole());
            u1.setEnabled(user.getEnabled());
            return userRepository.save(u1);
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }
}
