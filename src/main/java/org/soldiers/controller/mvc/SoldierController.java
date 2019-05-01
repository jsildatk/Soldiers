package org.soldiers.controller.mvc;

import org.soldiers.model.Mission;
import org.soldiers.model.News;
import org.soldiers.model.User;
import org.soldiers.repository.MissionRepository;
import org.soldiers.repository.NewsRepository;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/soldier")
public class SoldierController {
    private UserRepository userRepository;
    private NewsRepository newsRepository;
    private MissionRepository missionRepository;

    @Autowired
    public SoldierController(UserRepository userRepository, NewsRepository newsRepository, MissionRepository missionRepository) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.missionRepository = missionRepository;
    }

    @GetMapping("")
    public String soldierHomePage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        News news = newsRepository.findFirstByOrderByAddDateDesc();
        model.addAttribute("user", user);
        model.addAttribute("news", news);
        return "soldier/index";
    }

    @GetMapping("/missions")
    public String soldierMissionsPage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        List<Mission> missions = missionRepository.findByTeams(user.getSoldier().getTeam());
        model.addAttribute("user", user);
        model.addAttribute("missions", missions);
        return "soldier/missions";
    }
}
