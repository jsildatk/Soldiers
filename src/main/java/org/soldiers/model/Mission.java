package org.soldiers.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "mission_team",
            joinColumns = { @JoinColumn(name = "mission_id") },
            inverseJoinColumns = { @JoinColumn(name = "team_id") }
    )
    private Set<Team> teams = new HashSet<>();

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "commander_id", nullable = false)
    private Soldier commander;

    @Column(name = "mission", nullable = false)
    @Pattern(regexp = "[A-Z][a-z0-9]*", message = "Pole 'misja' musi zaczynać się z dużej litery")
    private String mission;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;


    public Mission(Set<Team> teams, Soldier commander, @Pattern(regexp = "[A-Z][a-z]*", message = "Pole 'misja' musi zaczynać się z dużej litery") String mission, Date startDate, Date endDate) {
        this.teams = teams;
        this.commander = commander;
        this.mission = mission;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Mission() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Soldier getCommander() {
        return commander;
    }

    public void setCommander(Soldier commander) {
        this.commander = commander;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Mission(" + this.id + " " + this.teams + " " + this.commander + " " + this.mission + " " + this.startDate + " " + this.endDate + ")";
    }
}
