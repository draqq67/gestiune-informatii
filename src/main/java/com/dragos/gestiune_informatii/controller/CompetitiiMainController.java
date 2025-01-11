/**
 * Clasa pentru CompetitiiMainController
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

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

import java.util.*;

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

    @GetMapping("/competitii")
    public String getAllCompetitions(Model model) {
        List<CompetitiiMain> competitions = competitiiMainService.getAllCompetitions();
        model.addAttribute("competitions", competitions);
        return "competitions/list";
    }

    @GetMapping("/competitii/{id}")
    public String getCompetitionDetails(@PathVariable Integer id, Model model) {
        CompetitiiMain competition = competitiiMainService.getCompetitionById(id);
        model.addAttribute("competition", competition);
        model.addAttribute("organizator", competition.organizator);

        if (competition.getNume() != null) {
            Object[] statistics = competitiiMainService.getCompetitionStatisticsByName(competition.getNume());
            model.addAttribute("statistics", statistics);
            System.out.println("Statistics: " + Arrays.toString(statistics));

        }
        return "competitions/details"; // Render the details of a single competition
    }
    @GetMapping("/competitii/{competitionId}/categorii/{categoryId}/participanti")
    public String getCategoryPlayers(@PathVariable Integer competitionId,
                                     @PathVariable Integer categoryId, Model model) {
        // Fetch the competition details
        CompetitiiMain competition = competitiiMainService.getCompetitionById(competitionId);
        model.addAttribute("competition", competition);

        // Fetch participants by category
        List<Object[]> participants = categoriiService.findParticipantiByCategoryId(categoryId);
        model.addAttribute("participants", participants);

        // Optionally, add category info to display on the page
        Categorii category = categoriiService.getCategoryByID(categoryId); // Assuming you have a service for categories
        model.addAttribute("category", category);

        return "competitions/category-players"; // Return the view for the category's participants
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("competitii/adauga")
    public String showNewCompetitionForm(Model model) {
        model.addAttribute("competitie", new CompetitiiMain());
        return "competitions/add"; // Return the add competition form
    }

    // POST: Handle the form submission for adding a new competition
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("competitii/adauga")
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
        return "redirect:/competitii"; // Redirects to the list of competitions page
    }

    @GetMapping("/competitii/{id}/categorii")
    @ResponseBody
    public List<Object[]> getCategoriiByCompetitie(@PathVariable("id") Long competitieId) {
        return categoriiService.findCategoriiByCompetitie(competitieId);
    }
    @GetMapping("/competitii/participanti")
    public String viewParticipants(@RequestParam(value = "competitionId", required = false) Integer competitionId, Model model) {
        // Fetch all competitions for the dropdown in the UI
        List<CompetitiiMain> competitions = competitiiMainService.getAllCompetitions();
        model.addAttribute("competitions", competitions);

        // If a specific competition is selected, fetch its participants
        if (competitionId != null) {
            List<Object[]> participants = competitiiMainService.getParticipantsByCompetitionId(competitionId);
            model.addAttribute("participants", participants);
        }

        return "competitions/view-participants-competition"; // Thymeleaf template name
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("competitii/sterge")
    public String showDeleteForm(Model model) {
        // Add a list of all competitions to the model for selection
        model.addAttribute("competitions", competitiiMainService.getAllCompetitions());
        return "competitions/delete"; // Render delete form page
    }

    @PostMapping("competitii/sterge")
    public String deleteCompetition(@RequestParam String competitionName, Model model) {
        try {
            competitiiMainService.deleteCompetitionAndRelatedData(competitionName);
            model.addAttribute("message", "Competition and related data deleted successfully.");
        } catch (Exception e) {
            model.addAttribute("message", "Error deleting competition: " + e.getMessage());
        }
        return "competitions/delete";
    }


}