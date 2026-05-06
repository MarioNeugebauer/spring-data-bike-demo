package org.htwdresden.informatik.pka.springdatabikedemo.controller;

import org.htwdresden.informatik.pka.springdatabikedemo.dto.BikeDto;
import org.htwdresden.informatik.pka.springdatabikedemo.service.BikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Bike
 */
@RestController
@RequestMapping("/api/bikes")
@Validated
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    /** CREATE */
    @PostMapping
    public ResponseEntity<BikeDto> create(@RequestBody BikeDto bikeDto) {
        BikeDto created = bikeService.createBike(bikeDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /** READ – all */
    @GetMapping
    public List<BikeDto> getAll() {
        return bikeService.getAllBikes();
    }

    /** READ – specific with ID */
    @GetMapping("/{id}")
    public ResponseEntity<BikeDto> getById(@PathVariable Long id) {
        return bikeService.getBike(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** UPDATE */
    @PutMapping("/{id}")
    public ResponseEntity<BikeDto> update(@PathVariable Long id,
                                          @RequestBody BikeDto bikeDto) {
        try {
            BikeDto updated = bikeService.updateBike(id, bikeDto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /** DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            bikeService.deleteBike(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}