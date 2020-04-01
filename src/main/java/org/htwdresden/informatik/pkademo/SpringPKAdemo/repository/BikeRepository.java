package org.htwdresden.informatik.pkademo.SpringPKAdemo.repository;

import org.htwdresden.informatik.pkademo.SpringPKAdemo.model.Bike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BikeRepository extends CrudRepository<Bike, Long> {

    Bike findByFrameNumber(String frameNumber);

    List<Bike> findByWheelSizeInchGreaterThanEqual(Integer wheelSizeInch);
    List<Bike> findByWheelSizeInchLessThanEqual(Integer wheelSizeInch);
    List<Bike> findByFrameSizeGreaterThanEqual(Integer frameSize);
    List<Bike> findByFrameSizeLessThanEqual(Integer frameSize);

    List<Bike> findByType(String type);

    @Query("select b from Bike b where b.type = ?1 and b.frameSize >= ?2")
    List<Bike> findByTypeAndFrameSizeGreaterThan(String type, Integer frameSize);

}
