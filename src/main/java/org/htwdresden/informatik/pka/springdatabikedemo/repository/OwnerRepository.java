package org.htwdresden.informatik.pka.springdatabikedemo.repository;

import org.htwdresden.informatik.pka.springdatabikedemo.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findByFirstname(String firstname);
}
