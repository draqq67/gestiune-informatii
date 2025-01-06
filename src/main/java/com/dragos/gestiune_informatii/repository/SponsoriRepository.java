package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.Sponsori;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsoriRepository extends CrudRepository<Sponsori, Integer> {
    // You can add custom queries here if needed, for example:
    // List<Sponsorii> findByCompetition_Id(Integer competitionId);
}
