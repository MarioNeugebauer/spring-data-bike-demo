package org.htwdresden.informatik.pka.springdatabikedemo.service;

import org.htwdresden.informatik.pka.springdatabikedemo.dto.BikeDto;
import org.htwdresden.informatik.pka.springdatabikedemo.mapper.BikeMapper;
import org.htwdresden.informatik.pka.springdatabikedemo.model.Bike;
import org.htwdresden.informatik.pka.springdatabikedemo.model.Owner;
import org.htwdresden.informatik.pka.springdatabikedemo.repository.BikeRepository;
import org.htwdresden.informatik.pka.springdatabikedemo.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service‑Klasse für Bike‑Operationen.
 */
@Service
public class BikeService {

    private final BikeRepository bikeRepo;
    private final OwnerRepository ownerRepo;

    public BikeService(BikeRepository bikeRepo, OwnerRepository ownerRepo) {
        this.bikeRepo = bikeRepo;
        this.ownerRepo = ownerRepo;
    }

    /** CREATE */
    @Transactional
    public BikeDto createBike(BikeDto dto) {
        Owner owner = ownerRepo.findById(dto.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        Bike bike = new Bike();
        bike.setOwner(owner);
        bike.setType(dto.getType());
        bike.setWheelSizeInch(dto.getWheelSizeInch());
        bike.setFrameSize(dto.getFrameSize());
        // frameNumber wird hier aus dem DTO nicht übergeben – ggf. automatisch generieren:
        bike.setFrameNumber(generateFrameNumber());

        Bike saved = bikeRepo.save(bike);
        return BikeMapper.toDto(saved);
    }

    /** READ – alle */
    @Transactional(readOnly = true)
    public List<BikeDto> getAllBikes() {
        return bikeRepo.findAll()
                .stream()
                .map(BikeMapper::toDto)
                .collect(Collectors.toList());
    }

    /** READ – nach Id */
    @Transactional(readOnly = true)
    public Optional<BikeDto> getBike(Long id) {
        return bikeRepo.findById(id).map(BikeMapper::toDto);
    }

    /** UPDATE */
    @Transactional
    public BikeDto updateBike(Long id, BikeDto dto) {
        Bike existing = bikeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bike not found"));

        // optional: Ownerwechsel erlaubt?
        if (dto.getOwnerId() != null && !dto.getOwnerId().equals(existing.getOwner().getId())) {
            Owner newOwner = ownerRepo.findById(dto.getOwnerId())
                    .orElseThrow(() -> new IllegalArgumentException("New owner not found"));
            existing.setOwner(newOwner);
        }

        existing.setType(dto.getType());
        existing.setWheelSizeInch(dto.getWheelSizeInch());
        existing.setFrameSize(dto.getFrameSize());
        // frameNumber bleibt unverändert (sicherheitskritisch)

        Bike saved = bikeRepo.save(existing);
        return BikeMapper.toDto(saved);
    }

    /** DELETE */
    @Transactional
    public void deleteBike(Long id) {
        if (!bikeRepo.existsById(id)) {
            throw new IllegalArgumentException("Bike not found");
        }
        bikeRepo.deleteById(id);
    }

    /** Hilfsmethode: zufällige Frame‑Nummer (nur Demo‑Zweck) */
    private String generateFrameNumber() {
        // 12‑stellige zufällige alphanumerische Zeichenkette
        return java.util.UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12);
    }
}