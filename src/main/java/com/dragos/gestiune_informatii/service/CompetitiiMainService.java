package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.*;
import com.dragos.gestiune_informatii.repository.CategoriiRepository;
import com.dragos.gestiune_informatii.repository.CompetitiiMainRepository;
import com.dragos.gestiune_informatii.repository.OrganizatoriRepository;
import com.dragos.gestiune_informatii.repository.PozeRepository;
import com.dragos.gestiune_informatii.repository.SponsoriRepository;
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
    private CategoriiRepository categoriiRepository;

    @Autowired
    private PozeRepository pozeRepository;

    @Autowired
    private SponsoriRepository sponsoriiRepository;

    @Autowired
    private OrganizatoriRepository organizatoriRepository;

    public List<CompetitiiMain> getAllCompetitions() {
        return competitiiMainRepository.findAllCompetitions();
    }

    public CompetitiiMain getCompetitionById(Integer id) {
        return competitiiMainRepository.findCompetitionById(id);
    }

    // Method to add a new competition along with related categories, images, and sponsors
    @Transactional
    public void addCompetitie(CompetitiiMain competitie,
                              List<String> sport,
                              List<Integer> numarEchipe,
                              List<String> urlPoza,
                              List<String> altText,
                              List<String> denumire,
                              List<String> tipPachet,
                              String organizerName) {

        // Check if the organizer exists in the database
        Organizatori organizator = organizatoriRepository.findByName(organizerName);

        // If the organizer does not exist, create a new one
        if (organizator == null) {
            organizator = new Organizatori();
            organizator.setName(organizerName);
            organizatoriRepository.save(organizator); // Save the new organizer to the database
        }

        // Associate the organizer with the competition
        competitie.setOrganizator(organizator);

        // Save the main competition
        competitiiMainRepository.save(competitie);

        // Add categories
        for (int i = 0; i < sport.size(); i++) {
            Categorii categorii = new Categorii();
            categorii.setSport(sport.get(i));
            categorii.setNumarEchipe(numarEchipe.get(i));
            categorii.setCompetition(competitie);  // Associate category with competition
            categoriiRepository.save(categorii);
        }

        // Add images
        for (int i = 0; i < urlPoza.size(); i++) {
            Poze poze = new Poze();
            poze.setUrl(urlPoza.get(i));
            poze.setAltText(altText.get(i));
            poze.setCompetitiiMain(competitie);  // Associate image with competition
            pozeRepository.save(poze);
        }

        // Add sponsors
        for (int i = 0; i < denumire.size(); i++) {
            Sponsori sponsorii = new Sponsori();
            sponsorii.setDenumire(denumire.get(i));
            sponsorii.setTip_pachet(tipPachet.get(i));
            sponsorii.setCompetition(competitie);  // Associate sponsor with competition
            sponsoriiRepository.save(sponsorii);
        }
    }


}

