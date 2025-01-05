package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.Echipe;
import com.dragos.gestiune_informatii.repository.EchipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchipeService {

    private final EchipeRepository echipeRepository;

    @Autowired
    public EchipeService(EchipeRepository echipeRepository) {
        this.echipeRepository = echipeRepository;
    }

    public List<Echipe> getAllTeams() {
        return echipeRepository.findAllTeams();
    }

    public List<Echipe> getTeamsByName(String name) {
        return echipeRepository.findTeamsByName(name);
    }

    public List<Echipe> getTeamsByYear(Integer year) {
        return echipeRepository.findTeamsByYear(year);
    }
}
