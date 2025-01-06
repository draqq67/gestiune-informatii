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
    @Query(value = """
    SELECT m.id_echipa1,
           m.id_echipa2,
           (SELECT e1.nume FROM echipe e1 WHERE e1.id = m.id_echipa1) AS team1_name,
           (SELECT e2.nume FROM echipe e2 WHERE e2.id = m.id_echipa2) AS team2_name,
           m.data_meci,
           m.scor1,
           m.scor2
    FROM meciuri m
    WHERE m.id_locatie = :locationId
    """, nativeQuery = true)
    List<Object[]> findMatchesByLocation(@Param("locationId") Integer locationId);

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
    @Query(value = "INSERT INTO meciuri (data_meci, id_echipa1, id_echipa2, id_locatie, status, scor1, scor2, id_categorie) " +
            "VALUES (:dataMeci, :echipa1, :echipa2, :locatie, :status, :scor1,:scor2, :categorie)", nativeQuery = true)
    void insertMatch(@Param("dataMeci") LocalDateTime dataMeci,
                     @Param("echipa1") Integer echipa1,
                     @Param("echipa2") Integer echipa2,
                     @Param("locatie") Integer locatie,
                     @Param("status") String status,
                     @Param("scor1") Integer scor1,
                     @Param("scor2") Integer scor2,
                     @Param("categorie") Integer categorie);

    @Modifying
    @Transactional
    @Query("UPDATE Meciuri m SET m.scor1 = :scor1, m.scor2 = :scor2 WHERE m.id = :id")
    void updateMatchScores(@Param("id") Integer id,
                           @Param("scor1") Integer scor1,
                           @Param("scor2") Integer scor2);

    // Query to get a match by its ID
    @Query("SELECT m FROM Meciuri m WHERE m.id = :id")
    Meciuri getMatchById(@Param("id") Integer id);
}

