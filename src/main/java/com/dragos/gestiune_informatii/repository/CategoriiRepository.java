/**
 * Clasa pentru CategoriiRepository
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */
package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.Categorii;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriiRepository extends CrudRepository<Categorii, Integer> {

    @Query("SELECT c FROM Categorii c")
    List<Categorii> findAllCategories();  // Custom query to fetch all categories

    @Query("SELECT c.id, c.sport FROM Categorii c WHERE c.competition.id = :competitieId")
    List<Object[]> findByCompetitieId(Long competitieId);

    @Query(value = """
        SELECT DISTINCT p.nume AS participant_name, e.nume AS team_name
        FROM participanti p
        INNER JOIN echipe e ON p.id_echipa = e.id
        INNER JOIN meciuri m ON m.id_echipa1 = e.id or m.id_echipa2 = e.id
        INNER JOIN categorii c ON m.id_categorie = c.id
        WHERE c.id = :categoryId
    """, nativeQuery = true)
    List<Object[]> findParticipantsByCategoryId(@Param("categoryId") Integer categoryId);

    @Query(value = """
    Select c FROM Categorii c where c.id = :categoryId
""")
    Categorii findByCategoryId(Integer categoryId);

}
