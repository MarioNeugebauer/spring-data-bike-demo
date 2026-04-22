package org.htwdresden.informatik.pka.springdatabikedemo.repository;

import org.htwdresden.informatik.pka.springdatabikedemo.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findByFirstname(String firstname);
}
