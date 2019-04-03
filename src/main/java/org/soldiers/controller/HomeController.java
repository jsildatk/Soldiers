package org.soldiers.controller;

import org.soldiers.model.Soldier;
import org.soldiers.repository.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private SoldierRepository soldierRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Soldier> soldiers = soldierRepository.findByUserNull();
        model.addAttribute("soldiers", soldiers);
        return "home";
    }
}
