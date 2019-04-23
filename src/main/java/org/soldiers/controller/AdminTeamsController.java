package org.soldiers.controller;

import org.soldiers.model.Mission;
import org.soldiers.model.Soldier;
import org.soldiers.model.Team;
import org.soldiers.repository.MissionRepository;
import org.soldiers.repository.SoldierRepository;
import org.soldiers.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/admin/teams")
@RestController
public class AdminTeamsController {
    private TeamRepository teamRepository;
    private MissionRepository missionRepository;
    private SoldierRepository soldierRepository;

    @Autowired
    public AdminTeamsController(TeamRepository teamRepository, MissionRepository missionRepository, SoldierRepository soldierRepository) {
        this.teamRepository = teamRepository;
        this.missionRepository = missionRepository;
        this.soldierRepository = soldierRepository;
    }

    @GetMapping("/missions/{id}")
    public List<Mission> getMissions(@PathVariable Long id) {
        return missionRepository.findByTeams(teamRepository.findById(id).get());
    }

    @GetMapping("/soldiers/{id}")
    public List<Soldier> getSoldiers(@PathVariable Long id) {
        return soldierRepository.findByTeam(teamRepository.findById(id).get());
    }

    @GetMapping("/team/{team}")
    public Team getTeamByTeam(@PathVariable String team) {
        return teamRepository.findByTeam(team);
    }

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable Long id) {
        return teamRepository.findById(id).get();
    }

    @PostMapping("")
    public Object addTeam(@ModelAttribute("formTeam") @Valid Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        return teamRepository.save(team);
    }

    @PutMapping("/{id}")
    public Object updateTeam(@PathVariable Long id, @ModelAttribute("formTeam") @Valid Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        Team t1 = teamRepository.findById(id).get();
        t1.setTeam(team.getTeam());
        return teamRepository.save(t1);
    }


    @DeleteMapping("/{id}")
    public String deleteTeam(@PathVariable Long id) {
        try {
            for (Soldier s : soldierRepository.findByTeam(teamRepository.findById(id).get())) {
                s.setTeam(null);
            }
            teamRepository.deleteById(id);
            return "Usunięto grupę o id: " + id;
        } catch (Exception e) {
            e.printStackTrace();
            return "Coś poszło nie tak";
        }
    }
}
