package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.model.*;
import com.dragos.gestiune_informatii.repository.CompetitiiMainRepository;
import com.dragos.gestiune_informatii.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CompetitiiMainController {

    @Autowired
    private CompetitiiMainService competitiiMainService;

    @Autowired
    private CategoriiService categoriiService;

    @Autowired
    private PozeService pozeService;

    @Autowired
    private CompetitiiMainRepository competitiiMainRepository;

    @Autowired
    private SponsoriService sponsoriService;

    @Autowired
    private OrganizatoriService organizatoriService;

    @GetMapping("/competitions")
    public String getAllCompetitions(Model model) {
        List<CompetitiiMain> competitions = competitiiMainService.getAllCompetitions();
        model.addAttribute("competitions", competitions);
        return "competitions/list";
    }
    @GetMapping("/competitions/{id}")
    public String getCompetitionDetails(@PathVariable Integer id, Model model) {
        CompetitiiMain competition = competitiiMainService.getCompetitionById(id);
        model.addAttribute("competition", competition);
        model.addAttribute("organizator", competition.organizator);

        return "competitions/details"; // Render the details of a single competition
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("competitions/add")
    public String showNewCompetitionForm(Model model) {
        model.addAttribute("competitie", new CompetitiiMain());
        return "competitions/add"; // Return the add competition form
    }

    // POST: Handle the form submission for adding a new competition
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("competitions/add")
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
            @RequestParam("sports") List<String> sports,
            @RequestParam("teamNumbers") List<Integer> teamNumbers,
            @RequestParam("imageUrls") List<String> imageUrls,
            @RequestParam("altTexts") List<String> altTexts,
            @RequestParam("sponsorNames") List<String> sponsorNames,
            @RequestParam("sponsorPackages") List<String> sponsorPackages,
            Model model) {

        // Call the service method to add the competition
        competitiiMainService.addCompetitieWithDetails(
                competitionName, dataStart, dataEnd, descriere, detalii, site, status, type, organizerName,
                sports, teamNumbers, imageUrls, altTexts, sponsorNames, sponsorPackages);

        model.addAttribute("message", "Competition added successfully!");

        // Redirect to the competition list after adding
        return "redirect:/competitions"; // Redirects to the list of competitions page
    }
}