/**
 * Clasa pentru OrganizatoriRepository
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.Organizatori;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrganizatoriRepository extends CrudRepository<Organizatori, Integer> {

    // Find organizer by name
    // Find organizer by name
//    @Query("Select Organizatori from Organizatori where Organizatori.name=:name")
    Optional<Organizatori> findByName(String name);

    // Insert new organizer if it doesn't exist
    @Modifying
    @Transactional
    @Query("INSERT INTO Organizatori (name) VALUES (:organizerName)")
    void insertNewOrganizer(String organizerName);
}
