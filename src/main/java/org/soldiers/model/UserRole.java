package org.soldiers.model;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Long userRoleId;

    @Column(nullable = false, name = "role")
    private String role;

    public UserRole(String role) {
        this.role = role;
    }

    public UserRole() {}

    public Long getId() {
        return userRoleId;
    }

    public void setId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.userRoleId + " " + this.role;
    }
}
