package org.soldiers.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team", nullable = false)
    @Pattern(regexp = "[A-Z][a-z]*", message = "Pole 'grupa' musi się zaczynać z dużej litery oraz nie może zawierać cyfr")
    private String team;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "teams")
    Set<Mission> missions = new HashSet<>();

    public Team(@Pattern(regexp = "[A-Z][a-z]*", message = "Pole 'grupa' musi się zaczynać z dużej litery oraz nie może zawierać cyfr") String team, Set<Mission> missions) {
        this.team = team;
        this.missions = missions;
    }

    public Team() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Set<Mission> getMissions() {
        return missions;
    }

    public void setMissions(Set<Mission> missions) {
        this.missions = missions;
    }

    @Override
    public String toString() {
        return this.team;
    }
}
