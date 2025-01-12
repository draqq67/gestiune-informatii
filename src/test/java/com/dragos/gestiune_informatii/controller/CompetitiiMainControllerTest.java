package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.model.Categorii;
import com.dragos.gestiune_informatii.model.CompetitiiMain;
import com.dragos.gestiune_informatii.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CompetitiiMainControllerTest {

    @InjectMocks
    private CompetitiiMainController competitiiMainController;

    @Mock
    private CompetitiiMainService competitiiMainService;

    @Mock
    private CategoriiService categoriiService;

    @Mock
    private PozeService pozeService;

    @Mock
    private SponsoriService sponsoriService;

    @Mock
    private OrganizatoriService organizatoriService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCompetitions() {
        // Arrange
        List<CompetitiiMain> competitions = new ArrayList<>();
        when(competitiiMainService.getAllCompetitions()).thenReturn(competitions);

        // Act
        String viewName = competitiiMainController.getAllCompetitions(model);

        // Assert
        assertEquals("competitions/list", viewName);
        verify(competitiiMainService, times(1)).getAllCompetitions();
        verify(model, times(1)).addAttribute("competitions", competitions);
    }

    @Test
    void getCompetitionDetails() {
        // Arrange
        Integer competitionId = 1;
        CompetitiiMain competition = new CompetitiiMain();
        competition.setNume("Test Competition");
        when(competitiiMainService.getCompetitionById(competitionId)).thenReturn(competition);
        Object[] statistics = {10, 5};
        when(competitiiMainService.getCompetitionStatisticsByName("Test Competition")).thenReturn(statistics);

        // Act
        String viewName = competitiiMainController.getCompetitionDetails(competitionId, model);

        // Assert
        assertEquals("competitions/details", viewName);
        verify(competitiiMainService, times(1)).getCompetitionById(competitionId);
        verify(model, times(1)).addAttribute("competition", competition);
        verify(model, times(1)).addAttribute("statistics", statistics);
    }

    @Test
    void getCategoryPlayers() {
        // Arrange
        Integer competitionId = 1;
        Integer categoryId = 2;
        CompetitiiMain competition = new CompetitiiMain();
        Categorii category = new Categorii();
        List<Object[]> participants = new ArrayList<>();

        when(competitiiMainService.getCompetitionById(competitionId)).thenReturn(competition);
        when(categoriiService.findParticipantiByCategoryId(categoryId)).thenReturn(participants);
        when(categoriiService.getCategoryByID(categoryId)).thenReturn(category);

        // Act
        String viewName = competitiiMainController.getCategoryPlayers(competitionId, categoryId, model);

        // Assert
        assertEquals("competitions/category-players", viewName);
        verify(model, times(1)).addAttribute("competition", competition);
        verify(model, times(1)).addAttribute("participants", participants);
        verify(model, times(1)).addAttribute("category", category);
    }

    @Test
    void showNewCompetitionForm() {
        // Act
        String viewName = competitiiMainController.showNewCompetitionForm(model);

        // Assert
        assertEquals("competitions/add", viewName);
        verify(model, times(1)).addAttribute(eq("competitie"), any(CompetitiiMain.class));
    }

    @Test
    void addCompetitie() {
        // Arrange
        String competitionName = "Competition Name";
        Date dataStart = new Date();
        Date dataEnd = new Date();
        String descriere = "Description";
        String detalii = "Details";
        String site = "http://example.com";
        boolean status = true;
        String type = "Type";
        String organizerName = "Organizer Name";
        List<String> sports = List.of("Sport1", "Sport2");
        List<Integer> teamNumbers = List.of(1, 2);
        List<String> imageUrls = List.of("http://image1.com", "http://image2.com");
        List<String> altTexts = List.of("Alt1", "Alt2");
        List<String> sponsorNames = List.of("Sponsor1", "Sponsor2");
        List<String> sponsorPackages = List.of("Package1", "Package2");

        // Act
        String viewName = competitiiMainController.addCompetitie(
                competitionName, dataStart, dataEnd, descriere, detalii, site, status, type, organizerName,
                sports, teamNumbers, imageUrls, altTexts, sponsorNames, sponsorPackages, model);

        // Assert
        assertEquals("redirect:/competitii", viewName);
        verify(competitiiMainService, times(1)).addCompetitieWithDetails(
                competitionName, dataStart, dataEnd, descriere, detalii, site, status, type, organizerName,
                sports, teamNumbers, imageUrls, altTexts, sponsorNames, sponsorPackages);
    }

    @Test
    void getCategoriiByCompetitie() {
        // Arrange
        Long competitieId = 1L;
        List<Object[]> categorii = new ArrayList<>();
        when(categoriiService.findCategoriiByCompetitie(competitieId)).thenReturn(categorii);

        // Act
        List<Object[]> result = competitiiMainController.getCategoriiByCompetitie(competitieId);

        // Assert
        assertEquals(categorii, result);
        verify(categoriiService, times(1)).findCategoriiByCompetitie(competitieId);
    }

    @Test
    void viewParticipants() {
        // Arrange
        Integer competitionId = 1;
        List<CompetitiiMain> competitions = new ArrayList<>();
        List<Object[]> participants = new ArrayList<>();
        when(competitiiMainService.getAllCompetitions()).thenReturn(competitions);
        when(competitiiMainService.getParticipantsByCompetitionId(competitionId)).thenReturn(participants);

        // Act
        String viewName = competitiiMainController.viewParticipants(competitionId, model);

        // Assert
        assertEquals("competitions/view-participants-competition", viewName);
        verify(model, times(1)).addAttribute("competitions", competitions);
        verify(model, times(1)).addAttribute("participants", participants);
    }

    @Test
    void showDeleteForm() {
        // Arrange
        List<CompetitiiMain> competitions = new ArrayList<>();
        when(competitiiMainService.getAllCompetitions()).thenReturn(competitions);

        // Act
        String viewName = competitiiMainController.showDeleteForm(model);

        // Assert
        assertEquals("competitions/delete", viewName);
        verify(model, times(1)).addAttribute("competitions", competitions);
    }

    @Test
    void deleteCompetition() {
        // Arrange
        String competitionName = "Test Competition";

        // Act
        String viewName = competitiiMainController.deleteCompetition(competitionName, model);

        // Assert
        assertEquals("competitions/delete", viewName);
        verify(competitiiMainService, times(1)).deleteCompetitionAndRelatedData(competitionName);
    }
}
