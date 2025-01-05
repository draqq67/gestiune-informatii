package com.dragos.gestiune_informatii.repository;

import com.dragos.gestiune_informatii.model.Categorii;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriiRepository extends CrudRepository<Categorii, Integer> {

    @Query("SELECT c FROM Categorii c")
    List<Categorii> findAllCategories();  // Custom query to fetch all categories
}
