package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.service.CompetitiiMainService;
import com.dragos.gestiune_informatii.model.CompetitiiMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/competitions")
public class CompetitiiMainController {

    @Autowired
    private CompetitiiMainService competitiiMainService;

    @GetMapping
    public String getAllCompetitions(Model model) {
        List<CompetitiiMain> competitions = competitiiMainService.getAllCompetitions();
        model.addAttribute("competitions", competitions);
        return "competitions/list"; // Render a list of all competitions
    }

    @GetMapping("/{id}")
    public String getCompetitionDetails(@PathVariable Integer id, Model model) {
        CompetitiiMain competition = competitiiMainService.getCompetitionById(id);
        model.addAttribute("competition", competition);
        model.addAttribute("organizator", competition.organizator);
        return "competitions/details"; // Render the details of a single competition
    }

    // Show the form for adding a new competition (only accessible by ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showNewCompetitionForm(Model model) {
        model.addAttribute("competitie", new CompetitiiMain());
        return "competitions/add"; // Show the add competition form
    }

    // Insert a new competition (POST request)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addCompetitie(
            @RequestParam("competitionName") String competitionName,
            @RequestParam("dataStart") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStart,
            @RequestParam("dataEnd") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataEnd,
            @RequestParam("descriere") String descriere,
            @RequestParam("detalii") String detalii,
            @RequestParam("site") String site,
            @RequestParam("status") boolean status,
            @RequestParam("type") String type,
            @RequestParam("organizerName") String organizerName,
            Model model) {

        // Call the service method to add the competition
        competitiiMainService.addCompetitie(
                competitionName, dataStart, dataEnd, descriere, detalii, site, status, type, organizerName);

        model.addAttribute("message", "Competition added successfully!");

        // Redirect to competition list after adding the competition
        return "redirect:/competitions"; // Redirects to the list of competitions page
    }
}

