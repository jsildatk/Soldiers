package org.soldiers.controller;

import org.soldiers.model.News;
import org.soldiers.model.Soldier;
import org.soldiers.model.User;
import org.soldiers.repository.NewsRepository;
import org.soldiers.repository.SoldierRepository;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("user", user);
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("soldier", new Soldier());
        return "admin/soldiers";
    }

    @GetMapping("/searchByLastName/{lastName}")
    @ResponseBody
    public List<Soldier> getSoldierByLastName(@PathVariable String lastName) {
        return soldierRepository.findByLastName(lastName);
    }
}
