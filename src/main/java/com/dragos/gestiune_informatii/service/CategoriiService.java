/**
 * Clasa pentru CategoriiService
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.Categorii;
import com.dragos.gestiune_informatii.repository.CategoriiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriiService {

    private final CategoriiRepository categoriiRepository;

    @Autowired
    public CategoriiService(CategoriiRepository categoriiRepository) {
        this.categoriiRepository = categoriiRepository;
    }

    public List<Categorii> getAllCategories() {
        return categoriiRepository.findAllCategories();
    }
    public Categorii addCategorie(Categorii categorii) {
        return categoriiRepository.save(categorii);
    }

    public List<Object[]> findCategoriiByCompetitie(Long competitieId) {
        return categoriiRepository.findByCompetitieId(competitieId);

    }

    public List<Object[]> findParticipantiByCategoryId(Integer competitieId) {
        return categoriiRepository.findParticipantsByCategoryId(competitieId);
    }

    public Categorii getCategoryByID(Integer categoryId) {
        return categoriiRepository.findByCategoryId(categoryId);
    }

}
