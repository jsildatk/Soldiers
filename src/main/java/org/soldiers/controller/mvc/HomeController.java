package org.soldiers.controller.mvc;

import org.soldiers.model.Address;
import org.soldiers.model.Soldier;
import org.soldiers.model.User;
import org.soldiers.repository.AddressRepository;
import org.soldiers.repository.SoldierRepository;
import org.soldiers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private SoldierRepository soldierRepository;
    private UserRepository userRepository;
    private AddressRepository addressRepository;

    @Autowired
    public HomeController(SoldierRepository soldierRepository, UserRepository userRepository, AddressRepository addressRepository) {
        this.soldierRepository = soldierRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Soldier> soldiers = soldierRepository.findByUserNull();
        model.addAttribute("soldiers", soldiers);
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/settings")
    public String userSettingsPage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "settings";
    }

    @GetMapping("/personalData")
    public String soldierPersonalDataPage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        List<Address> addresses = addressRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("soldier", user.getSoldier());
        model.addAttribute("addresses", addresses);
        return "personalData";
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
