package org.htwdresden.informatik.pka.springdatabikedemo.controller;

import org.htwdresden.informatik.pka.springdatabikedemo.dto.OwnerDto;
import org.htwdresden.informatik.pka.springdatabikedemo.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Owner
 */
@RestController
@RequestMapping("/api/owners")
@Validated
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /** CREATE */
    @PostMapping
    public ResponseEntity<OwnerDto> create(@RequestBody OwnerDto ownerDto) {
        OwnerDto created = ownerService.createOwner(ownerDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /** READ – all */
    @GetMapping
    public List<OwnerDto> getAll() {
        return ownerService.getAllOwners();
    }

    /** READ – specific ID */
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable Long id) {
        return ownerService.getOwner(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /** UPDATE */
    @PutMapping("/{id}")
    public ResponseEntity<OwnerDto> update(@PathVariable Long id,
                                           @RequestBody OwnerDto dto) {
        try {
            OwnerDto updated = ownerService.updateOwner(id, dto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /** DELETE */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            ownerService.deleteOwner(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}