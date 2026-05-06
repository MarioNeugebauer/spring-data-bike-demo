package org.htwdresden.informatik.pka.springdatabikedemo.service;

import org.htwdresden.informatik.pka.springdatabikedemo.dto.OwnerDto;
import org.htwdresden.informatik.pka.springdatabikedemo.mapper.OwnerMapper;
import org.htwdresden.informatik.pka.springdatabikedemo.model.Owner;
import org.htwdresden.informatik.pka.springdatabikedemo.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service‑Klasse für Owner‑Operationen.
 */
@Service
public class OwnerService {

    private final OwnerRepository ownerRepo;

    public OwnerService(OwnerRepository ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    /** CREATE */
    @Transactional
    public OwnerDto createOwner(OwnerDto dto) {
        Owner owner = new Owner();
        owner.setFirstname(dto.getFirstname());
        owner.setLastname(dto.getLastname());
        // street & city bleiben null – sie werden bewusst nicht vom Client übermittelt
        Owner saved = ownerRepo.save(owner);
        return OwnerMapper.toDto(saved);
    }

    /** READ – alle */
    @Transactional(readOnly = true)
    public List<OwnerDto> getAllOwners() {
        return ownerRepo.findAll()
                .stream()
                .map(OwnerMapper::toDto)
                .collect(Collectors.toList());
    }

    /** READ – nach Id */
    @Transactional(readOnly = true)
    public Optional<OwnerDto> getOwner(Long id) {
        return ownerRepo.findById(id).map(OwnerMapper::toDto);
    }

    /** UPDATE */
    @Transactional
    public OwnerDto updateOwner(Long id, OwnerDto dto) {
        Owner existing = ownerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        existing.setFirstname(dto.getFirstname());
        existing.setLastname(dto.getLastname());
        // street/city bleiben unverändert (nicht im DTO)
        Owner saved = ownerRepo.save(existing);
        return OwnerMapper.toDto(saved);
    }

    /** DELETE */
    @Transactional
    public void deleteOwner(Long id) {
        if (!ownerRepo.existsById(id)) {
            throw new IllegalArgumentException("Owner not found");
        }
        ownerRepo.deleteById(id);
    }
}