package org.soldiers.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "street", nullable = false)
    @Pattern(regexp = "\\p{Lu}\\p{L}*\\s[0-9/]*", message = "Pole 'ulica' musi zaczynać się z dużej litery oraz mieć format ulica_numer lokalu")
    private String street;

    @Column(name = "city", nullable = false)
    @Pattern(regexp = "\\p{Lu}\\p{L}*", message = "Pole 'miasto' musi zaczynać się z dużej litery oraz może zawierać tylko litery")
    private String city;

    @Column(name = "postal_code", nullable = false)
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "Pole 'kod pocztowy' musi mieć format xx-xxx oraz może zawierać tylko cyfry")
    private String postalCode;

    public Address(String street, String city, String postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Address() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address(" +  this.id + " " + this.street + " " + this.city + " " + this.postalCode + ")";
    }
}
