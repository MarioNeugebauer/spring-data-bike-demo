package org.htwdresden.informatik.pka.springdatabikedemo.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String street;
    private String city;

    @OneToMany(mappedBy = "owner")
    private Set<Bike> bikes;


    protected Owner() {}

    public Owner(String firstname, String lastname, String street, String city) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.city = city;
    }

    public String toString() {
        return String.format("Owner[firstname=%s, lastname=%s]", firstname, lastname);
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStreet() { return street; }

    public String getCity() { return city; }

    public Set<Bike> getBikes() {
        if(this.bikes == null) {
            return new HashSet<Bike>();
        }
        return this.bikes;
    }
}
