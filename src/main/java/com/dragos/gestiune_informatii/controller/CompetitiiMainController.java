package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.model.*;
import com.dragos.gestiune_informatii.service.CompetitiiMainService;
import com.dragos.gestiune_informatii.service.CategoriiService;
import com.dragos.gestiune_informatii.service.PozeService;
import com.dragos.gestiune_informatii.service.SponsoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/competitions")
public class CompetitiiMainController {

    @Autowired
    private CompetitiiMainService competitiiMainService;

    @Autowired
    private CategoriiService categoriiService;  // Service to handle categorii (categories)

    @Autowired
    private PozeService pozeService;  // Service to handle images (poze)

    @Autowired
    private SponsoriService sponsoriiService;  // Service to handle sponsors

    @GetMapping("/competitions")
    public String getAllCompetitions(Model model) {
        List<CompetitiiMain> competitions = competitiiMainService.getAllCompetitions();
        model.addAttribute("competitions", competitions);
        return "competitions/list"; // Render a list of all competitions
    }

    @GetMapping("competitions/{id}")
    public String getCompetitionDetails(@PathVariable Integer id, Model model) {
        CompetitiiMain competition = competitiiMainService.getCompetitionById(id);
        model.addAttribute("competition", competition);
        model.addAttribute("organizator", competition.getOrganizator());
        return "competitions/details"; // Render the details of a single competition
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("competitions/add")
    public String showNewCompetitionForm(Model model) {
        System.out.println("GET /competitions/add called");
        model.addAttribute("competitie", new CompetitiiMain());
        return "competitions/add"; // Show the add competition form
    }

    // Insert a new competition (POST request)
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("competitions/add")
    public String addCompetitie(
            @ModelAttribute CompetitiiMain competitie,
            @RequestParam List<String> sport,
            @RequestParam List<Integer> numarEchipe,
            @RequestParam List<String> urlPoza,
            @RequestParam List<String> altText,
            @RequestParam List<String> denumire,
            @RequestParam List<String> tipPachet,
            @RequestParam String organizerName,  // Add this field to capture the organizer name
            Model model) {
        System.out.println("POST /competitions/add called");

        try {
            // Call the service method to add the competition along with associated categories, images, sponsors, and organizer
            competitiiMainService.addCompetitie(competitie, sport, numarEchipe, urlPoza, altText, denumire, tipPachet, organizerName);

            // Add success message to the model
            model.addAttribute("message", "Competition added successfully!");
        } catch (Exception e) {
            // Add error message to the model in case of an exception
            System.err.println("Error: " + e.getMessage());
            model.addAttribute("message", "Error: " + e.getMessage());
        }
        // Redirect to competition list after adding the competition
        return "redirect:/competitions"; // Redirect or render as needed
    }

}
