/**
 * Clasa pentru PozeRepository
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */
package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.Poze;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PozeRepository extends CrudRepository<Poze, Integer> {
    // You can add custom queries here if needed, for example:
    // List<Poze> findByCompetitiiMain_Id(Integer competitionId);
}
