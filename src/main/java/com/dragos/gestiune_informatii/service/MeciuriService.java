/**
 * Clasa pentru MeciuriService
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.Meciuri;
import com.dragos.gestiune_informatii.repository.MeciuriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class MeciuriService {

    private final MeciuriRepository meciuriRepository;

    @Autowired
    public MeciuriService(MeciuriRepository meciuriRepository) {
        this.meciuriRepository = meciuriRepository;
    }

    public List<Meciuri> getAllMatches() {
        return meciuriRepository.findAllMatches();
    }

    public List<Meciuri> getMatchesByCategory(Integer categoryId) {
        return meciuriRepository.findMatchesByCategory(categoryId);
    }

    public List<Object[]> getMatchesByLocation(Integer locationId) {
        return meciuriRepository.findMatchesByLocation(locationId);
    }

    public List<Meciuri> getAllMatchesWithDetails() {
        return meciuriRepository.findAllMatchesWithDetails();
    }
    // Use custom query to insert match

    public void insertMatch(LocalDateTime dataMeci, Integer echipa1, Integer echipa2, Integer locatie,
                            String status, Integer scor1,Integer scor2, Integer categorie) {
        meciuriRepository.insertMatch(dataMeci, echipa1, echipa2, locatie, status, scor1,scor2, categorie);
    }

    public void updateMatch(Meciuri match) {
        meciuriRepository.updateMatchScores(match.getId(),match.getScor1(),match.getScor2());
    }

    public Meciuri getMatchById(Integer id) {
        return meciuriRepository.getMatchById(id);
    }

    public List<Meciuri> getMatchesByStatus(String active) {
        return meciuriRepository.findMatchesByStatus(active);
    }

    public List<Meciuri> getMatchesAndCompetitions(Long competitieId) {
        return meciuriRepository.findMatchesAndCompetitionsByCompetitieId(competitieId);
    }
    public void updateStatusMatch(Meciuri match) {
        meciuriRepository.updateStatus(match.getId(),match.getStatus());
    }
}
