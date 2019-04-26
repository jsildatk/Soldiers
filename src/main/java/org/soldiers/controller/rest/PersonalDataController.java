package org.soldiers.controller.rest;

import org.soldiers.model.Soldier;
import org.soldiers.repository.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/personalData")
public class PersonalDataController {
    private SoldierRepository soldierRepository;

    @Autowired
    public PersonalDataController(SoldierRepository soldierRepository) {
        this.soldierRepository = soldierRepository;
    }

    @PutMapping("/{id}")
    public Object updatePersonalData(@PathVariable Long id, @Valid Soldier soldier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            Soldier s1 = soldierRepository.findById(id).get();
            s1.setFirstName(soldier.getFirstName());
            s1.setLastName(soldier.getLastName());
            s1.setPersonalEvidenceNumber(soldier.getPersonalEvidenceNumber());
            s1.setBirthDate(soldier.getBirthDate());
            s1.setAddress(soldier.getAddress());
            return soldierRepository.save(s1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
