package org.htwdresden.informatik.pka.springdatabikedemo.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Owner.
 * <p>
 * The fields {@code street} and {@code city} are ommitted
 * due to data privacy and will not be sent to the client.
 */
public class OwnerDto {

    private Long id;
    private String firstname;
    private String lastname;

    // optional: List of all Bike‑IDs, in case that Client needs to see this assignment
    private java.util.Set<Long> bikeIds;

    // ----- constructors ----------------------------------------------------
    public OwnerDto() {
    }

    public OwnerDto(Long id, String firstname, String lastname,
                    java.util.Set<Long> bikeIds) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.bikeIds = bikeIds;
    }

    // ----- getters / setters -----------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public java.util.Set<Long> getBikeIds() {
        return bikeIds;
    }

    public void setBikeIds(java.util.Set<Long> bikeIds) {
        this.bikeIds = bikeIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerDto)) return false;
        OwnerDto ownerDto = (OwnerDto) o;
        return Objects.equals(id, ownerDto.id) &&
                Objects.equals(firstname, ownerDto.firstname) &&
                Objects.equals(lastname, ownerDto.lastname) &&
                Objects.equals(bikeIds, ownerDto.bikeIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, bikeIds);
    }

    @Override
    public String toString() {
        return "OwnerDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", bikeIds=" + bikeIds +
                '}';
    }
}