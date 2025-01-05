package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.Meciuri;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface MeciuriRepository extends CrudRepository<Meciuri, Integer> {

    // Query to get all matches
    @Query("SELECT m FROM Meciuri m")
    List<Meciuri> findAllMatches();

    // Query to find matches by category
    @Query("SELECT m FROM Meciuri m WHERE m.categorie.id = :categoryId")
    List<Meciuri> findMatchesByCategory(@Param("categoryId") Integer categoryId);

    // Query to find matches by location
    @Query("SELECT m FROM Meciuri m WHERE m.locatie.id = :locationId")
    List<Meciuri> findMatchesByLocation(@Param("locationId") Integer locationId);

    // Query to find matches by status
    @Query("SELECT m FROM Meciuri m WHERE m.status = :status")
    List<Meciuri> findMatchesByStatus(@Param("status") String status);

    @Query("""

            SELECT m FROM Meciuri m
           JOIN FETCH m.categorie c
           JOIN FETCH m.echipa1 e1
           JOIN FETCH m.echipa2 e2
           JOIN FETCH m.locatie l
           """)
    List<Meciuri> findAllMatchesWithDetails();

    // Custom insert query
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO meciuri (data_meci, id_echipa1, id_echipa2, id_locatie, status, scor, id_categorie) " +
            "VALUES (:dataMeci, :echipa1, :echipa2, :locatie, :status, :scor, :categorie)", nativeQuery = true)
    void insertMatch(@Param("dataMeci") LocalDateTime dataMeci,
                     @Param("echipa1") Integer echipa1,
                     @Param("echipa2") Integer echipa2,
                     @Param("locatie") Integer locatie,
                     @Param("status") String status,
                     @Param("scor") String scor,
                     @Param("categorie") Integer categorie);

    }
