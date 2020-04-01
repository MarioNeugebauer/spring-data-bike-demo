package org.htwdresden.informatik.pkademo.SpringPKAdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String frameNumber;
    private Integer wheelSizeInch;
    private Integer frameSize;

    protected Bike() {}

    public Bike(String type, String frameNumber, Integer wheelSizeInch, Integer frameSize) {
        this.type = type;
        this.frameNumber = frameNumber;
        this.wheelSizeInch = wheelSizeInch;
        this.frameSize = frameSize;
    }

    public Bike(String type, String frameNumber) {
        this.type = type;
        this.frameNumber = frameNumber;
    }

    @Override
    public String toString() {
        return String.format("Bike[frameNumber=%s]",frameNumber);
    }

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

}

