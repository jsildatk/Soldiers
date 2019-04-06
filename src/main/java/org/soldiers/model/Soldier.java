package org.soldiers.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "soldier")
public class Soldier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soldier_id")
    private Long soldierId;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "rank_id", nullable = false)
    private Rank rank;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "personal_evidence_number", nullable = false, unique = true, length = 11)
    private String personalEvidenceNumber;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    public Soldier(User user, Rank rank, Address address, Team team, String firstName, String lastName, String personalEvidenceNumber, Date birthDate) {
        this.user = user;
        this.rank = rank;
        this.address = address;
        this.team = team;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalEvidenceNumber = personalEvidenceNumber;
        this.birthDate = birthDate;
    }

    public Soldier() {}

    public Long getSoldierId() {
        return soldierId;
    }

    public void setSoldierId(Long soldierId) {
        this.soldierId = soldierId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalEvidenceNumber() {
        return personalEvidenceNumber;
    }

    public void setPersonalEvidenceNumber(String personalEvidenceNumber) {
        this.personalEvidenceNumber = personalEvidenceNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "UserId: " + this.user.getUserRoleId() + "\nAddress: " + this.address.getStreet() + " " + this.address.getCity() + " " + this.address.getPostalCode() + "\n" +
                "Rank: " + this.rank.getRank() + "\nTeam: " + this.team.getTeam() + "\nSoldier: " + this.firstName + " " + this.lastName + " " + this.personalEvidenceNumber +
                " " + this.birthDate;
    }
}