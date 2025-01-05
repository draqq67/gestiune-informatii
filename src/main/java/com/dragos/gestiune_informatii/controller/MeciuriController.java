package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.model.*;
import com.dragos.gestiune_informatii.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class MeciuriController {

    private final MeciuriService meciuriService;
    private final EchipeService echipeService;
    private final LocatiiService locatiiService;
    private final CategoriiService categoriiService;
    private final CompetitiiMainService competitiiMainService;

    @Autowired
    public MeciuriController(MeciuriService meciuriService,
                             EchipeService echipeService,
                             LocatiiService locatiiService,
                             CategoriiService categoriiService,
                             CompetitiiMainService competitiiMainService) {
        this.meciuriService = meciuriService;
        this.echipeService = echipeService;
        this.locatiiService = locatiiService;
        this.categoriiService = categoriiService;
        this.competitiiMainService = competitiiMainService;
    }

    // Show add match form with all teams, categories, locations, and competitions
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/meciuri/add")
    public String showAddMatchForm(Model model) {
        List<Echipe> echipe = echipeService.getAllTeams();
        List<Categorii> categorii = categoriiService.getAllCategories();
        List<Locatii> locatii = locatiiService.getAllLocations();
        List<CompetitiiMain> competitii = competitiiMainService.getAllCompetitions();

        model.addAttribute("meciuri", new Meciuri());  // Empty Meciuri object to bind form
        model.addAttribute("echipe", echipe);  // All available teams
        model.addAttribute("categorii", categorii);  // All available categories
        model.addAttribute("locatii", locatii);  // All available locations
        model.addAttribute("competitii", competitii);  // All available competitions

        return "addMeciuri";  // The template for adding a match
    }

    // Handle the form submission for adding a new match
    @PostMapping("/meciuri/add")
    public String addNewMatch(
            @RequestParam("dataMeci") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataMeci,
            @RequestParam("echipa1") Integer echipa1,
            @RequestParam("echipa2") Integer echipa2,
            @RequestParam("locatie") Integer locatie,
            @RequestParam("status") String status,
            @RequestParam("scor") String scor,
            @RequestParam("categorie") Integer categorie,
            Model model) {
        try {
            model.addAttribute("meciuri", new Meciuri());
            meciuriService.insertMatch(dataMeci, echipa1, echipa2, locatie, status, scor, categorie);
            model.addAttribute("success", "Match added successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error adding match: " + e.getMessage());
        }
        return "addMeciuri"; // Return the appropriate view
    }
    @GetMapping("/meciuri")
    public String showMeciuri(Model model) {
//        List<CompetitiiMain> competitions = competitiiMainService.getAllCompetitions();
        List<Meciuri> meciuri = meciuriService.getAllMatches();
        model.addAttribute("meciuri", meciuri);
        return "meciuri";
    }
}
