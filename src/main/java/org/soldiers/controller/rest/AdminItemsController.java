package org.soldiers.controller.rest;

import org.soldiers.model.Item;
import org.soldiers.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/admin/items")
public class AdminItemsController {
    private ItemRepository itemRepository;
    private Environment environment;

    private boolean deleteFile(File file) {
        return file.delete();
    }

    private void saveFile(MultipartFile file, Path path) throws IOException {
        file.transferTo(path);
    }

    @Autowired
    public AdminItemsController(ItemRepository itemRepository, Environment environment) {
        this.itemRepository = itemRepository;
        this.environment = environment;
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemRepository.findById(id).get();
    }

    @PostMapping(value = "", headers = "content-type=multipart/*")
    public Item addItem(@RequestParam("item") String item, @RequestParam("description") String description, @RequestParam("image") MultipartFile image, HttpServletResponse httpServletResponse) {
        try {
            saveFile(image, Paths.get(environment.getProperty("image.upload") + image.getOriginalFilename()));
            Item i = new Item();
            i.setItem(item);
            i.setDescription(description);
            i.setImage("/images/" + image.getOriginalFilename());
            httpServletResponse.setStatus(201);
            return itemRepository.save(i);
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping(value = "/{id}", headers = "content-type=multipart/*")
    public Item updateItem(@PathVariable Long id, @RequestParam("item") String item, @RequestParam("description") String description, @RequestParam("image") MultipartFile image, HttpServletResponse httpServletResponse) {
        try {
            Item i = itemRepository.findById(id).get();
            i.setItem(item);
            i.setDescription(description);
            if (deleteFile(new File("src/main/resources/static" + i.getImage()))) {
                i.setImage("/images/" + image.getOriginalFilename());
                saveFile(image, Paths.get(environment.getProperty("image.upload") + image.getOriginalFilename()));
                return itemRepository.save(i);
            } else {
                httpServletResponse.setStatus(409);
                return null;
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        try {
            Item i = itemRepository.findById(id).get();
            if (deleteFile(new File("src/main/resources/static" + i.getImage()))) {
                itemRepository.delete(i);
                return "Usunięto przedmiot o id: " + id;
            } else {
                httpServletResponse.setStatus(409);
                return "Nie można usunąć pliku";
            }
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return "Coś poszło nie tak";
        }
    }
}
