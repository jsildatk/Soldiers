package org.soldiers.controller.rest;

import org.soldiers.model.News;
import org.soldiers.repository.NewsRepository;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/news/rest")
public class NewsRestController {
    private NewsRepository newsRepository;
    private UserRepository userRepository;

    private java.sql.Timestamp getCurrentDate() {
        java.util.Date d = new java.util.Date();
        return new java.sql.Timestamp(d.getTime());
    }

    @Autowired
    public NewsRestController(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/title/{title}")
    public News getNewsByTitle(@PathVariable String title) {
        return newsRepository.findByTitle(title);
    }

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
        return newsRepository.findById(id).get();
    }

    @PostMapping("")
    public Object addNews(@ModelAttribute("formNews") @Valid News news, BindingResult bindingResult, Principal principal, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            news.setUser(userRepository.findByUsername(principal.getName()));
            news.setAddDate(getCurrentDate());
            httpServletResponse.setStatus(201);
            return newsRepository.save(news);
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public Object updateNews(@PathVariable Long id, @ModelAttribute("formNews") @Valid News news, BindingResult bindingResult, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            News n1 = newsRepository.findById(id).get();
            n1.setTitle(news.getTitle());
            n1.setContent(news.getContent());
            n1.setAddDate(getCurrentDate());
            return newsRepository.save(n1);
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteNews(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        try {
            newsRepository.deleteById(id);
            return "Usnięto ogłoszenie o id: " + id;
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return "Coś poszło nie tak";
        }
    }
}
