package org.soldiers.controller.mvc;

import org.soldiers.model.Item;
import org.soldiers.model.User;
import org.soldiers.repository.ItemRepository;
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
@RequestMapping("/items")
public class ItemsController {
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    @Autowired
    public ItemsController(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String itemsPage(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        List<Item> items = itemRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/{id}")
    public String itemInfoPage(@PathVariable Long id, Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("item", itemRepository.findById(id).get());
        return "itemInfo";
    }
}
