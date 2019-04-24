package org.soldiers.controller.mvc;

import org.soldiers.model.News;
import org.soldiers.model.User;
import org.soldiers.repository.NewsRepository;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    private NewsRepository newsRepository;
    private UserRepository userRepository;

    @Autowired
    public NewsController(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String newsPage(Principal principal, Model model) {
        List<News> news = newsRepository.findAll();
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("news", news);
        model.addAttribute("user", user);
        return "news";
    }

    @GetMapping("/{id}")
    public String newsInfoPage(@PathVariable Long id, Principal principal, Model model) {
        News news = newsRepository.findById(id).get();
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("news", news);
        model.addAttribute("user", user);
        return "newsInfo";
    }

}