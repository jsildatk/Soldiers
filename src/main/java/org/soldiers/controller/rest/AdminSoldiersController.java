package org.soldiers.controller.rest;

import org.soldiers.model.Soldier;
import org.soldiers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public Object addSoldier(@Valid Soldier soldier, BindingResult bindingResult, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            httpServletResponse.setStatus(201);
            return soldierRepository.save(soldier);
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public Object updateSoldier(@PathVariable Long id, @Valid Soldier soldier, BindingResult bindingResult, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            Soldier s1 = soldierRepository.findById(id).get();
            s1.setFirstName(soldier.getFirstName());
            s1.setLastName(soldier.getLastName());
            s1.setPersonalEvidenceNumber(soldier.getPersonalEvidenceNumber());
            s1.setBirthDate(soldier.getBirthDate());
            s1.setRank(soldier.getRank());
            s1.setAddress(soldier.getAddress());
            s1.setTeam(soldier.getTeam());
            return soldierRepository.save(s1);
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteSoldierById(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        try {
            soldierRepository.deleteById(id);
            return "Usunięto żolnierza o id: " + id;
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return "Coś poszło nie tak";
        }
    }
}
