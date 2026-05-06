package org.htwdresden.informatik.pka.springdatabikedemo.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Bike.
 * <p>
 * The field  {@code frameNumber} is ommitted by purpose
 * since it should not be forwarded to the client.
 */
public class BikeDto {

    private Long id;
    private String type;
    private Integer wheelSizeInch;
    private Integer frameSize;
    private Long ownerId;          // Referenz zum Owner (nur ID)

    // ----- constructors ----------------------------------------------------
    public BikeDto() {
    }

    public BikeDto(Long id, String type, Integer wheelSizeInch,
                   Integer frameSize, Long ownerId) {
        this.id = id;
        this.type = type;
        this.wheelSizeInch = wheelSizeInch;
        this.frameSize = frameSize;
        this.ownerId = ownerId;
    }

    // ----- getters / setters -----------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWheelSizeInch() {
        return wheelSizeInch;
    }

    public void setWheelSizeInch(Integer wheelSizeInch) {
        this.wheelSizeInch = wheelSizeInch;
    }

    public Integer getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(Integer frameSize) {
        this.frameSize = frameSize;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BikeDto)) return false;
        BikeDto bikeDto = (BikeDto) o;
        return Objects.equals(id, bikeDto.id) &&
                Objects.equals(type, bikeDto.type) &&
                Objects.equals(wheelSizeInch, bikeDto.wheelSizeInch) &&
                Objects.equals(frameSize, bikeDto.frameSize) &&
                Objects.equals(ownerId, bikeDto.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, wheelSizeInch, frameSize, ownerId);
    }

    @Override
    public String toString() {
        return "BikeDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", wheelSizeInch=" + wheelSizeInch +
                ", frameSize=" + frameSize +
                ", ownerId=" + ownerId +
                '}';
    }
}