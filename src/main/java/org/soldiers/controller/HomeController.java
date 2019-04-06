package org.soldiers.controller;

import org.soldiers.model.Soldier;
import org.soldiers.model.User;
import org.soldiers.repository.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController implements ErrorController {
    @Autowired
    private SoldierRepository soldierRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Soldier> soldiers = soldierRepository.findByUserNull();
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    @Override
    @ResponseBody
    public String getErrorPath() {
        return "/error";
    }
}
