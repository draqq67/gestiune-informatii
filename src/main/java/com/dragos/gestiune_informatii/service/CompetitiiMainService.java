/**
 * Clasa pentru CompetitiiMainService
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.Categorii;
import com.dragos.gestiune_informatii.model.CompetitiiMain;
import com.dragos.gestiune_informatii.model.Categorii;
import com.dragos.gestiune_informatii.model.Poze;
import com.dragos.gestiune_informatii.model.Sponsori;
import com.dragos.gestiune_informatii.model.Organizatori;
import com.dragos.gestiune_informatii.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompetitiiMainService {

    @Autowired
    private CompetitiiMainRepository competitiiMainRepository;

    @Autowired
    private OrganizatoriRepository organizatoriRepository;

    @Autowired
    private CategoriiRepository categoriiRepository;

    @Autowired
    private PozeRepository pozeRepository;

    @Autowired
    private SponsoriRepository sponsorRepository;

    public List<CompetitiiMain> getAllCompetitions() {
        return competitiiMainRepository.findAllCompetitions();
    }

    @Transactional
    public void addCompetitieWithDetails(String competitionName, Date dataStart, Date dataEnd, String descriere,
                                         String detalii, String site, boolean status, String type, String organizerName,
                                         List<String> sports, List<Integer> teamNumbers, List<String> imageUrls,
                                         List<String> altTexts, List<String> sponsorNames, List<String> sponsorPackages) {

        // Check if the organizer exists
        Organizatori organizer = organizatoriRepository.findByName(organizerName).orElseGet(() -> {
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

        // Save categories (sports and number of teams)
        if (sports != null && teamNumbers != null && sports.size() == teamNumbers.size()) {
            for (int i = 0; i < sports.size(); i++) {
                Categorii category = new Categorii();
                category.setSport(sports.get(i));
                category.setNumarEchipe(teamNumbers.get(i));
                category.setCompetition(competition);
                categoriiRepository.save(category);
            }
        }

        // Save images
        if (imageUrls != null && altTexts != null && imageUrls.size() == altTexts.size()) {
            for (int i = 0; i < imageUrls.size(); i++) {
                Poze image = new Poze();
                image.setUrl(imageUrls.get(i));
                image.setAltText(altTexts.get(i));
                image.setCompetitiiMain(competition);
                pozeRepository.save(image);
            }
        }

        // Save sponsors
        if (sponsorNames != null && sponsorPackages != null && sponsorNames.size() == sponsorPackages.size()) {
            for (int i = 0; i < sponsorNames.size(); i++) {
                Sponsori sponsor = new Sponsori();
                sponsor.setDenumire(sponsorNames.get(i));
                sponsor.setTip_pachet(sponsorPackages.get(i));  // Fixed redundant line
                sponsor.setCompetition(competition);  // Make sure to associate the competition
                sponsorRepository.save(sponsor);
            }
        }
    }
    public CompetitiiMain getCompetitionById(Integer id) {
        return competitiiMainRepository.findCompetitionById(id);
    }

    public List<Object[]> getParticipantsByCompetitionId(Integer competitionId) {
        return competitiiMainRepository.findParticipantsByCompetitionId(competitionId);
    }

    public Object[] getCompetitionStatisticsByName(String name) {
        return competitiiMainRepository.getCompetitionStatisticsByName(name);
    }

    @Transactional
    public void deleteCompetitionAndRelatedData(String competitionName) {
        competitiiMainRepository.deleteCompetitionAndRelatedData(competitionName);
    }



}

