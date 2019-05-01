package org.soldiers.controller.rest;

import org.soldiers.model.Mission;
import org.soldiers.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/missions")
public class MissionsController {
    private MissionRepository missionRepository;

    @Autowired
    public MissionsController(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @GetMapping("/{id}")
    public Mission getMissionById(@PathVariable Long id) {
        return missionRepository.findById(id).get();
    }

    @GetMapping("/mission/{mission}")
    public Mission getMissionByMission(@PathVariable String mission) {
        return missionRepository.findByMission(mission);
    }

    @PostMapping("")
    public Object addMission(@ModelAttribute("missionForm") @Valid Mission mission, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            return missionRepository.save(mission);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public Object updateMission(@PathVariable Long id) {
        try {
            Mission m1 = missionRepository.findById(id).get();
            if (m1.getEndDate() != null) {
                return "Misja już jest zakończona";
            }
            java.util.Date d = new java.util.Date();
            java.sql.Date sd = new java.sql.Date(d.getTime());
            m1.setEndDate(sd);
            return missionRepository.save(m1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteMissionById(@PathVariable Long id) {
        try {
            missionRepository.deleteById(id);
            return "Usunięto misję o id: " + id;
        } catch (Exception e) {
            e.printStackTrace();
            return "Coś poszło nie tak";
        }
    }
}
