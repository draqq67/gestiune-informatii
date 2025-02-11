/**
 * Clasa pentru LocatiiRepository
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */
package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.Locatii;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocatiiRepository extends CrudRepository<Locatii, Integer> {

    @Query("SELECT l FROM Locatii l")
    List<Locatii> findAllLocations();  // Custom query to fetch all locations

}
