package org.htwdresden.informatik.pka.springdatabikedemo.mapper;

import org.htwdresden.informatik.pka.springdatabikedemo.dto.BikeDto;
import org.htwdresden.informatik.pka.springdatabikedemo.model.Bike;
import org.htwdresden.informatik.pka.springdatabikedemo.model.Owner;

/**
 * Conversion between {@link Bike} and {@link BikeDto}.
 */
public final class BikeMapper {

    private BikeMapper() {
        // utility class – prevent instantiation
    }

    /**
     * Entity → DTO
     */
    public static BikeDto toDto(Bike bike) {
        if (bike == null) {
            return null;
        }
        Long ownerId = bike.getOwner() != null ? bike.getOwner().getId() : null;
        return new BikeDto(
                bike.getId(),
                bike.getType(),
                bike.getWheelSizeInch(),
                bike.getFrameSize(),
                ownerId
        );
    }

    /**
     * DTO → Entity.
     * <p>
     * The {@code owner} must be provided from the caller (Service),
     * since the DTO contains the ID only.
     */
    public static Bike toEntity(BikeDto dto, Owner owner) {
        if (dto == null) {
            return null;
        }
        Bike bike = new Bike();
        // Wir verwenden den geschützten No‑Args‑Konstruktor der Entity
        // (Spring JPA kann das Feld direkt setzen). Alternativ: Builder‑Pattern.
        bike.setOwner(owner);
        // Setzen der übrigen Felder
        // Hinweis: frameNumber wird hier bewusst nicht gesetzt (sollte
        // evtl. vom Service generiert oder aus einem anderen DTO kommen)
        bike.setId(dto.getId());               // ID wird von JPA verwaltet, hier nur für Updates
        bike.setType(dto.getType());
        bike.setWheelSizeInch(dto.getWheelSizeInch());
        bike.setFrameSize(dto.getFrameSize());

        return bike;
    }

    // Hilfsmethode, um die ID zu setzen (nur für Updates nötig)
    private static void setId(Bike bike, Long id) {
        try {
            java.lang.reflect.Field f = Bike.class.getDeclaredField("id");
            f.setAccessible(true);
            f.set(bike, id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to set id via reflection", e);
        }
    }
}