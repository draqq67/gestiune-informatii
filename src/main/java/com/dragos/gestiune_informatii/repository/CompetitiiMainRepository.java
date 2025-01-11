/**
 * Clasa pentru CompetitiiMainRepository
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */
package com.dragos.gestiune_informatii.repository;

    import com.dragos.gestiune_informatii.model.CompetitiiMain;
    import com.dragos.gestiune_informatii.model.Organizatori;
    import jakarta.transaction.Transactional;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.data.jpa.repository.Modifying;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.Date;
    import java.util.List;

    @Repository
    public interface CompetitiiMainRepository extends CrudRepository<CompetitiiMain, Integer> {

        // Corrected method to find organizer by name (assuming 'name' is the correct column)
        @Query("SELECT o.id FROM Organizatori o WHERE o.name = :organizerName")
        Integer findOrganizerIdByName(@Param("organizerName") String organizerName);

        // Method to find an organizer by name (for checking if it exists)
//        @Modifying
//        @Transactional
//        @Query("INSERT INTO CompetitiiMain (nume, data_start, data_end, descriere, detalii, site, status, type, organizator_id) " +
//                "VALUES (:competitionName, :dataStart, :dataEnd, :descriere, :detalii, :site, :status, :type, :organizerId)")
//        void insertCompetition(String competitionName, Date dataStart, Date dataEnd, String descriere,
//                               String detalii, String site, boolean status, String type, Integer organizerId);
       // Fetch all competitions
        @Query("SELECT c FROM CompetitiiMain c")
        List<CompetitiiMain> findAllCompetitions();

        @Query("SELECT c FROM CompetitiiMain c WHERE c.id = :id")
        CompetitiiMain findCompetitionById(@Param("id") Integer id);

        @Query(value = """
        SELECT p.id, p.nume, p.email, p.telefon
        FROM participanti p
        WHERE p.id_echipa IN (
            SELECT m.id_echipa1
            FROM meciuri m
            WHERE m.id_categorie IN (
                SELECT c.id
                FROM categorii c
                WHERE c.competition_id = :competitionId
            )
            UNION
            SELECT m.id_echipa2
            FROM meciuri m
            WHERE m.id_categorie IN (
                SELECT c.id
                FROM categorii c
                WHERE c.competition_id = :competitionId
            )
        )
        """, nativeQuery = true)
        List<Object[]> findParticipantsByCompetitionId(@Param("competitionId") Integer competitionId);

        // Get detailed statistics for a competition by competition name (total participants, matches, and categories)
        @Query(value = """
    SELECT 
        (SELECT COUNT(p.id) 
         FROM participanti p
         INNER JOIN echipe e ON p.id_echipa = e.id
         INNER JOIN meciuri m ON (m.id_echipa1 = e.id OR m.id_echipa2 = e.id)
         INNER JOIN categorii c ON c.id = m.id_categorie
         WHERE c.competition_id = cm.id) AS total_participants,
        (SELECT COUNT(m.id) 
         FROM meciuri m
         INNER JOIN categorii c ON c.id = m.id_categorie
         WHERE c.competition_id = cm.id) AS total_matches,
        (SELECT COUNT(c.id) 
         FROM categorii c
         WHERE c.competition_id = cm.id) AS total_categories
    FROM competitii_main cm
    WHERE cm.nume = :competitionName
""", nativeQuery = true)
        Object[] getCompetitionStatisticsByName(@Param("competitionName") String competitionName);

        @Modifying
        @Transactional
        @Query(value = """
        DELETE FROM Meciuri
           WHERE id_categorie IN (
               SELECT c.id
               FROM Categorii c
               WHERE c.competition_id = (
                   SELECT id
                   FROM Competitii_Main
                   WHERE nume = :competitionName
               )
           );        
        DELETE FROM sponsori WHERE competition_id = (SELECT id FROM competitii_main WHERE nume = :competitionName);
        DELETE FROM poze p WHERE p.id_competitie_main = (SELECT id FROM competitii_main WHERE nume = :competitionName);
        DELETE FROM categorii WHERE competition_id = (SELECT id FROM competitii_main WHERE nume = :competitionName);
        DELETE FROM competitii_main WHERE nume = :competitionName;
    
    """,
                nativeQuery = true)
        void deleteCompetitionAndRelatedData(@Param("competitionName") String competitionName);




    }
