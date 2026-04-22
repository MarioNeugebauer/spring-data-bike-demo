package org.htwdresden.informatik.pka.springdatabikedemo.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String frameNumber;
    private Integer wheelSizeInch;
    private Integer frameSize;

    @ManyToOne
    @JoinColumn(name="owner_id", nullable = false)
    private Owner owner;

    protected Bike() {}

    public Bike(String type, String frameNumber, Integer wheelSizeInch,
                Integer frameSize) {
        this.type = type;
        this.frameNumber = frameNumber;
        this.wheelSizeInch = wheelSizeInch;
        this.frameSize = frameSize;
    }

    public Bike(String type, String frameNumber) {
        this.type = type;
        this.frameNumber = frameNumber;
    }

    public String toString() {return String.format("Bike[frameNumber=%s]",frameNumber); }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public Integer getWheelSizeInch() {
        return wheelSizeInch;
    }

    public Integer getFrameSize() {
        return frameSize;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
