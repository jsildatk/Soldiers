package org.soldiers.controller;

import org.soldiers.model.*;
import org.soldiers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserRepository userRepository;
    private NewsRepository newsRepository;
    private SoldierRepository soldierRepository;
    private RankRepository rankRepository;
    private AddressRepository addressRepository;
    private TeamRepository teamRepository;
    private RoleRepository roleRepository;
    private MissionRepository missionRepository;

    @Autowired
    public AdminController(UserRepository userRepository, NewsRepository newsRepository, SoldierRepository soldierRepository, RankRepository rankRepository, AddressRepository addressRepository, TeamRepository teamRepository, RoleRepository roleRepository, MissionRepository missionRepository) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.soldierRepository = soldierRepository;
        this.rankRepository = rankRepository;
        this.addressRepository = addressRepository;
        this.teamRepository = teamRepository;
        this.roleRepository = roleRepository;
        this.missionRepository = missionRepository;
    }

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

    @GetMapping("/addresses")
    public String adminAddressesPage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        List<Address> addresses = addressRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("addresses", addresses);
        model.addAttribute("address", new Address());
        return "admin/addresses";
    }

    @GetMapping("/missions")
    public String adminMissionsPage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        List<Mission> missions = missionRepository.findAll();
        List<Soldier> soldiers = soldierRepository.findAll();
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("missions", missions);
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("teams", teams);
        model.addAttribute("missionForm", new Mission());
        return "admin/missions";
    }
}
