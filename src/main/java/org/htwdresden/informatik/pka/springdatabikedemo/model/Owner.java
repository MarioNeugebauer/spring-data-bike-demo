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

    @OneToMany(mappedBy = "owner")
    private Set<Bike> bikes;


    protected Owner() {}

    public Owner(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
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

    public Set<Bike> getBikes() {
        if(this.bikes == null) {
            return new HashSet<Bike>();
        }
        return this.bikes;
    }
}
