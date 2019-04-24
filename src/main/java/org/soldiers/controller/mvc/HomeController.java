package org.soldiers.controller.mvc;

import org.soldiers.model.Soldier;
import org.soldiers.model.User;
import org.soldiers.repository.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {
    private SoldierRepository soldierRepository;

    @Autowired
    public HomeController(SoldierRepository soldierRepository) {
        this.soldierRepository = soldierRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Soldier> soldiers = soldierRepository.findByUserNull();
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/403")
    @ResponseBody
    public String get403Page() {
        return "403";
    }

    @GetMapping("/loginError")
    public ModelAndView loginError(ModelAndView mav, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("loginError", true);
        mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping("/loginSuccess")
    public String successLogin(Authentication auth) {
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "redirect:/admin";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("SOLDIER"))) {
            return "redirect:/soldier";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("COMMANDER"))) {
            return "redirect:/commander";
        }
        return "redirect:/";
    }
}
