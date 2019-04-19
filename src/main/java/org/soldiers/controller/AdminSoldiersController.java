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
    private SoldierRepository soldierRepository;
    private RankRepository rankRepository;
    private AddressRepository addressRepository;
    private TeamRepository teamRepository;

    @Autowired
    public AdminSoldiersController(SoldierRepository soldierRepository, RankRepository rankRepository, AddressRepository addressRepository, TeamRepository teamRepository) {
        this.soldierRepository = soldierRepository;
        this.rankRepository = rankRepository;
        this.addressRepository = addressRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/lastName/{lastName}")
    public List<Soldier> getSoldiersByLastName(@PathVariable String lastName) {
        return soldierRepository.findByLastName(lastName);
    }

    @GetMapping("/{id}")
    public Soldier getSoldier(@PathVariable Long id) {
        return soldierRepository.findById(id).get();
    }

    @PostMapping("")
    public Object addSoldier(@Valid Soldier soldier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
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
    public Object updateSoldier(@PathVariable Long id, @Valid Soldier soldier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
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
