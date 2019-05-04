package org.soldiers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "news"})
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<News> news;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Soldier soldier;

    @Column(name = "username", nullable = false)
    @NotNull
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    @NotNull
    @Size(min = 8)
    @JsonIgnore
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "enabled", columnDefinition = "boolean default true", nullable = false, length = 1)
    private Boolean enabled = true;

    public User(Role role, Set<News> news, @NotNull String username, @NotNull @Size(min = 8) String password, @Email String email, Boolean enabled) {
        this.role = role;
        this.news = news;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Soldier getSoldier() {
        return soldier;
    }

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
    }

    @Override
    public String toString() {
        return "User(" + this.id + " " + this.username + " " + this.password + " " + this.email
                + " " + this.enabled + " " + this.role + ")";
    }
}
