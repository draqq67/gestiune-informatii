package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.Echipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EchipeRepository extends CrudRepository<Echipe, Integer> {

    // Query to get all teams
    @Query("SELECT e FROM Echipe e")
    List<Echipe> findAllTeams();

    // Query to find teams by name
    @Query("SELECT e FROM Echipe e WHERE e.nume = :name")
    List<Echipe> findTeamsByName(@Param("name") String name);

    // Query to find teams by establishment year
    @Query("SELECT e FROM Echipe e WHERE e.anInfiintare = :year")
    List<Echipe> findTeamsByYear(@Param("year") Integer year);

    @Query("SELECT e FROM Echipe e WHERE e.id =:id")
    Echipe findTeamById(@Param("id") Integer id);
}
