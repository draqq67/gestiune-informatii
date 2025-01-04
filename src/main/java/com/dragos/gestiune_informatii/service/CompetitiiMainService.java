package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.CompetitiiMain;
import com.dragos.gestiune_informatii.model.Organizatori;
import com.dragos.gestiune_informatii.repository.CompetitiiMainRepository;
import com.dragos.gestiune_informatii.repository.OrganizatoriRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitiiMainService {

    @Autowired
    private CompetitiiMainRepository competitiiMainRepository;

    @Autowired
    private OrganizatoriRepository organizatoriRepository;
    public List<CompetitiiMain> getAllCompetitions() {
        return competitiiMainRepository.findAllCompetitions();
    }

    public CompetitiiMain getCompetitionById(Integer id) {
        return competitiiMainRepository.findCompetitionById(id);
    }

    // Method to add a new competition
    @Transactional
    public void addCompetitie(String competitionName, Date dataStart, Date dataEnd, String descriere,
                              String detalii, String site, boolean status, String type, String organizerName) {
        // Check if the organizer exists
        Organizatori organizer = organizatoriRepository.findByName(organizerName)
                .orElseGet(() -> {
                    Organizatori newOrganizer = new Organizatori();
                    newOrganizer.setName(organizerName);
                    return organizatoriRepository.save(newOrganizer);
                });

        // Create the competition
        CompetitiiMain competition = new CompetitiiMain();
        competition.setNume(competitionName);
        competition.setDataStart(dataStart);
        competition.setDataEnd(dataEnd);
        competition.setDescriere(descriere);
        competition.setDetalii(detalii);
        competition.setSite(site);
        competition.setStatus(status);
        competition.setType(type);
        competition.setOrganizator(organizer);

        // Save the competition
        competitiiMainRepository.save(competition);
    }
}
