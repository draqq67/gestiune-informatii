package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.model.*;
import com.dragos.gestiune_informatii.repository.LocatiiRepository;
import com.dragos.gestiune_informatii.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final LocatiiRepository locatiiRepository;

    @Autowired
    public MeciuriController(MeciuriService meciuriService,
                             EchipeService echipeService,
                             LocatiiService locatiiService,
                             CategoriiService categoriiService,
                             CompetitiiMainService competitiiMainService, LocatiiRepository locatiiRepository) {
        this.meciuriService = meciuriService;
        this.echipeService = echipeService;
        this.locatiiService = locatiiService;
        this.categoriiService = categoriiService;
        this.competitiiMainService = competitiiMainService;
        this.locatiiRepository = locatiiRepository;
    }

    // Show add match form with all teams, categories, locations, and competitions
  @PreAuthorize("hasRole('ADMIN')")
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
        model.addAttribute("competitie", competitii);  // All available competitions
        return "meciuri/addMeciuri";  // The template for adding a match
    }

    // Handle the form submission for adding a new match
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/meciuri/add")
    public String addNewMatch(
            @RequestParam("dataMeci") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataMeci,
            @RequestParam("echipa1") Integer echipa1,
            @RequestParam("echipa2") Integer echipa2,
            @RequestParam("locatie") Integer locatie,
            @RequestParam("status") String status,
            @RequestParam("scor1") Integer scor1, // Added parameter for Team 1's score
            @RequestParam("scor2") Integer scor2, // Added parameter for Team 2's score
            @RequestParam("categorie") Integer categorie,
            Model model) {
        try {
            model.addAttribute("meciuri", new Meciuri());
            meciuriService.insertMatch(dataMeci, echipa1, echipa2, locatie, status, scor1,scor2,categorie);
            model.addAttribute("success", "Match added successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error adding match: " + e.getMessage());
        }
        return "meciuri/addMeciuri";
    }
    @GetMapping("/meciuri")
    public String showMeciuri(Model model) {
//        List<CompetitiiMain> competitions = competitiiMainService.getAllCompetitions();
        List<Meciuri> meciuri = meciuriService.getAllMatches();
        model.addAttribute("meciuri", meciuri);
        return "meciuri/meciuri";
    }
    @GetMapping("/meciuri/update/{id}")
    public String showUpdateMatchForm(@PathVariable("id") Integer id, Model model) {
        // Fetch the match from the database using the id
        Meciuri match = meciuriService.getMatchById(id);
        if (match != null) {
            model.addAttribute("match", match);  // Add match data to the model
            return "meciuri/updateMeciuri";  // The template for updating the match
        } else {
            model.addAttribute("error", "Match not found!");
            return "meciuri/active";  // Redirect to the list of matches page if the match is not found
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/meciuri/update/{id}")
    public String updateMatch(
            @PathVariable("id") Integer id,
            @RequestParam("scor1") Integer scor1,
            @RequestParam("scor2") Integer scor2,
            Model model) {
        try {
            // Find the match by its ID
            Meciuri match = meciuriService.getMatchById(id);
            if (match != null) {
                // Update the scores
                match.setScor1(scor1);
                match.setScor2(scor2);
                // Save the updated match to the database
                meciuriService.updateMatch(match);
                model.addAttribute("success", "Match scores updated successfully!");
            } else {
                model.addAttribute("error", "Match not found!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error updating match: " + e.getMessage());
        }
        return "redirect:/meciuri/active";  // Return to the update page with success or error message
    }
    @GetMapping("/meciuri/active")
    public String showActiveMatches(Model model) {
        List<Meciuri> activeMatches = meciuriService.getMatchesByStatus("active");  // Fetch active matches
        model.addAttribute("meciuri", activeMatches);
        return "meciuri/activeMeciuri";  // Template to display active matches
    }

    @GetMapping("/findByLocation")
    public String showMatchesPage(Model model, @RequestParam(required = false) Integer locationId) {
        List<Locatii> locations = locatiiRepository.findAllLocations(); // Fetch all locations for the search bar.
        model.addAttribute("locations", locations);

        if (locationId != null) {
            List<Object[]> matches = meciuriService.getMatchesByLocation(locationId);
            model.addAttribute("matches", matches);
        }

        return "meciuri/meciuriByLocation"; // Renders the "matches.html" page.
    }
}


