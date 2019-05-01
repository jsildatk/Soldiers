package org.soldiers.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "soldier")
public class Soldier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soldier_id")
    private Long id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "rank_id", nullable = false)
    private Rank rank;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, targetEntity = Item.class)
    @JoinTable(
            name = "item_soldier",
            joinColumns = { @JoinColumn(name = "soldier_id", referencedColumnName = "soldier_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id", referencedColumnName = "item_id") }
    )
    @JsonManagedReference
    private Set<Item> items = new HashSet<>();

    @Column(name = "first_name", nullable = false)
    @Pattern(regexp = "[A-Z][a-z]*", message = "Pole 'imię' musi zaczynać się z dużej litery oraz nie może zawierać cyfr")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = "[A-Z][a-z]*", message = "Pole 'nazwisko' musi zaczynać się z dużej litery oraz nie może zawierać cyfr")
    private String lastName;

    @Column(name = "personal_evidence_number", nullable = false, unique = true, length = 11)
    @Pattern(regexp = "[0-9]{11}", message = "Pole 'pesel' musi zawierać dokładnie 11 cyfr")
    private String personalEvidenceNumber;

    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "Pole nie może być puste")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
