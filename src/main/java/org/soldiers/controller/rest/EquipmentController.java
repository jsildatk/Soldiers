package org.soldiers.controller.rest;

import org.soldiers.model.Item;
import org.soldiers.model.Soldier;
import org.soldiers.repository.ItemRepository;
import org.soldiers.repository.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    private SoldierRepository soldierRepository;
    private ItemRepository itemRepository;

    @Autowired
    public EquipmentController(SoldierRepository soldierRepository, ItemRepository itemRepository) {
        this.soldierRepository = soldierRepository;
        this.itemRepository = itemRepository;
    }

    @PutMapping("/{id}")
    public Object updateItemsInSoldier(@PathVariable Long id, Soldier soldier) {
        try {
            Soldier s1 = soldierRepository.findById(id).get();
            if (soldier.getItems().size() + s1.getItems().size() > 7) {
                return "Liczba przedmiotów po tej operacji przekorczy 7!";
            }
            s1.getItems().addAll(soldier.getItems());
            return soldierRepository.save(s1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{itemId}/{soldierId}")
    public Soldier deleteItemInSoldier(@PathVariable Long itemId, @PathVariable Long soldierId) {
        try {
            Item i1 = itemRepository.findById(itemId).get();
            Soldier s1 = soldierRepository.findById(soldierId).get();
            s1.getItems().remove(i1);
            return soldierRepository.save(s1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
