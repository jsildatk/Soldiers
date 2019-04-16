package org.soldiers.controller;

import org.soldiers.model.Address;
import org.soldiers.model.Rank;
import org.soldiers.model.Soldier;
import org.soldiers.model.Team;
import org.soldiers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/soldiers")
public class AdminSoldiersController {
    @Autowired
    private SoldierRepository soldierRepository;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/searchByLastName/{lastName}")
    public List<Soldier> getSoldierByLastName(@PathVariable String lastName) {
        return soldierRepository.findByLastName(lastName);
    }

    @GetMapping("/{id}")
    public Soldier getSoldier(@PathVariable Long id) {
        return soldierRepository.findById(id).get();
    }

    @PostMapping("")
    public Soldier addSoldier(@Valid Soldier soldier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        try {
            Rank rank = rankRepository.findById(soldier.getRank().getId()).get();
            Address address = addressRepository.findById(soldier.getAddress().getId()).get();
            Team team = teamRepository.findById(soldier.getTeam().getId()).get();
            soldier.setRank(rank);
            soldier.setAddress(address);
            soldier.setTeam(team);
            soldierRepository.save(soldier);
            return soldier;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public Soldier updateSoldier(@PathVariable Long id, @Valid Soldier soldier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return null;
        }
        try {
            Soldier s1 = soldierRepository.findById(id).get();
            Rank rank = rankRepository.findById(soldier.getRank().getId()).get();
            Address address = addressRepository.findById(soldier.getAddress().getId()).get();
            Team team = teamRepository.findById(soldier.getTeam().getId()).get();
            s1.setFirstName(soldier.getFirstName());
            s1.setLastName(soldier.getLastName());
            s1.setPersonalEvidenceNumber(soldier.getPersonalEvidenceNumber());
            s1.setBirthDate(soldier.getBirthDate());
            s1.setRank(rank);
            s1.setAddress(address);
            s1.setTeam(team);
            soldierRepository.save(s1);
            return s1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteSoldierById(@PathVariable Long id) {
        try {
            soldierRepository.deleteById(id);
            return "Usunięto żolnierza o id: " + id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Coś poszło nie tak";
    }
}
