package org.htwdresden.informatik.pka.springdatabikedemo.mapper;

import org.htwdresden.informatik.pka.springdatabikedemo.dto.OwnerDto;
import org.htwdresden.informatik.pka.springdatabikedemo.model.Owner;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Konvertiert zwischen {@link Owner} und {@link OwnerDto}.
 */
public final class OwnerMapper {

    private OwnerMapper() {
    }

    public static OwnerDto toDto(Owner owner) {
        if (owner == null) {
            return null;
        }
        Set<Long> bikeIds = owner.getBikes()
                .stream()
                .map(b -> b.getId())
                .collect(Collectors.toSet());

        return new OwnerDto(
                owner.getId(),
                owner.getFirstname(),
                owner.getLastname(),
                bikeIds
        );
    }

    public static Owner toEntity(OwnerDto dto) {
        if (dto == null) {
            return null;
        }
        Owner owner = new Owner();
        // Setzen der ID (wie beim Bike-Mapper via Reflection)
        setId(owner, dto.getId());

        // Wir setzen nur die sichtbaren Felder; street/city bleiben leer,
        // weil sie im DTO nicht vorhanden sind.
        owner.setFirstname(dto.getFirstname());
        owner.setLastname(dto.getLastname());
        // bikes werden im Service später befüllt (wenn nötig)
        return owner;
    }

    private static void setId(Owner owner, Long id) {
        try {
            java.lang.reflect.Field f = Owner.class.getDeclaredField("id");
            f.setAccessible(true);
            f.set(owner, id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to set id via reflection", e);
        }
    }
}