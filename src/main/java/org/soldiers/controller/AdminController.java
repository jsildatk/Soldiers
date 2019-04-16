package org.soldiers.controller;

import org.soldiers.model.*;
import org.soldiers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private SoldierRepository soldierRepository;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public String adminHomePage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        News news = newsRepository.findFirstByOrderByAddDateDesc();
        model.addAttribute("user", user);
        model.addAttribute("news", news);
        return "admin/index";
    }

    @GetMapping("/soldiers")
    public String adminSoldiersPage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        List<Soldier> soldiers = soldierRepository.findAll();
        List<Rank> ranks = rankRepository.findAll();
        List<Address> addresses = addressRepository.findAll();
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("ranks", ranks);
        model.addAttribute("addresses", addresses);
        model.addAttribute("teams", teams);
        model.addAttribute("soldier", new Soldier());
        return "admin/soldiers";
    }

    @GetMapping("/users")
    public String adminUsersPage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        List<User> users = userRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("userForm", new User());
        return "admin/users";
    }
}
