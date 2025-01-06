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
}
