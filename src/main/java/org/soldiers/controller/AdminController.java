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
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("ranks", ranks);
        model.addAttribute("addresses", addresses);
        model.addAttribute("teams", teams);
        model.addAttribute("roles", roles);
        model.addAttribute("soldier", new Soldier());
        return "admin/soldiers";
    }

    @GetMapping("/searchByLastName/{lastName}")
    @ResponseBody
    public List<Soldier> getSoldierByLastName(@PathVariable String lastName) {
        return soldierRepository.findByLastName(lastName);
    }

    @DeleteMapping("/soldiers/{id}")
    @ResponseBody
    public String deleteSoldierById(@PathVariable Long id) {
        try {
            soldierRepository.deleteById(id);
            return "Usunięto żolnierza o id: " + id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Coś poszło nie tak";
    }

    @PostMapping("/soldiers")
    @ResponseBody
    public Soldier addSoldier(@Valid Soldier soldier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        try {
            Rank rank = rankRepository.findById(soldier.getRank().getId()).get();
            Address address = addressRepository.findById(soldier.getAddress().getId()).get();
            Team team = teamRepository.findById(soldier.getTeam().getId()).get();
            soldier.setRank(rank);
            soldier.setAddress(address);
            soldier.setTeam(team);
            soldierRepository.save(soldier);
            return soldier;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/soldiers/{id}")
    @ResponseBody
    public Soldier getSoldier(@PathVariable Long id) {
        return soldierRepository.findById(id).get();
    }

    @PutMapping("/soldiers")
    @ResponseBody
    public Soldier updateSoldier(@Valid Soldier soldier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        try {
            Rank rank = rankRepository.findById(soldier.getRank().getId()).get();
            Address address = addressRepository.findById(soldier.getAddress().getId()).get();
            Team team = teamRepository.findById(soldier.getTeam().getId()).get();
            soldier.setRank(rank);
            soldier.setAddress(address);
            soldier.setTeam(team);
            soldierRepository.save(soldier);
            return soldier;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
