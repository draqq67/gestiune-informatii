package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.model.*;
import com.dragos.gestiune_informatii.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MeciuriControllerTest {

    @InjectMocks
    private MeciuriController meciuriController;

    @Mock
    private MeciuriService meciuriService;

    @Mock
    private EchipeService echipeService;

    @Mock
    private LocatiiService locatiiService;

    @Mock
    private CategoriiService categoriiService;

    @Mock
    private CompetitiiMainService competitiiMainService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNewMatch() {
        // Arrange
        LocalDateTime matchDate = LocalDateTime.now();
        Integer team1 = 1, team2 = 2, locationId = 3, categoryId = 4;
        String status = "Active";
        Integer score1 = 3, score2 = 2;

        // Act
        String viewName = meciuriController.addNewMatch(
                matchDate, team1, team2, locationId, status, score1, score2, categoryId, model);

        // Assert
        assertEquals("meciuri/addMeciuri", viewName);
        verify(meciuriService, times(1)).insertMatch(matchDate, team1, team2, locationId, status, score1, score2, categoryId);
        verify(model, times(1)).addAttribute("success", "Match added successfully!");
    }

    @Test
    void showMeciuri() {
        // Arrange
        List<Meciuri> matches = new ArrayList<>();
        when(meciuriService.getAllMatches()).thenReturn(matches);

        // Act
        String viewName = meciuriController.showMeciuri(model);

        // Assert
        assertEquals("meciuri/meciuri", viewName);
        verify(model, times(1)).addAttribute("meciuri", matches);
    }

    @Test
    void showUpdateMatchForm() {
        // Arrange
        Integer matchId = 1;
        Meciuri match = new Meciuri();
        when(meciuriService.getMatchById(matchId)).thenReturn(match);

        // Act
        String viewName = meciuriController.showUpdateMatchForm(matchId, model);

        // Assert
        assertEquals("meciuri/updateMeciuri", viewName);
        verify(model, times(1)).addAttribute("match", match);
    }

    @Test
    void updateMatch() {
        // Arrange
        Integer matchId = 1;
        Integer score1 = 3, score2 = 4;
        String status = "Completed";
        Meciuri match = new Meciuri();
        when(meciuriService.getMatchById(matchId)).thenReturn(match);

        // Act
        String viewName = meciuriController.updateMatch(matchId, score1, score2, status, model);

        // Assert
        assertEquals("redirect:/meciuri/active", viewName);
        verify(meciuriService, times(1)).updateMatch(match);
        verify(meciuriService, times(1)).updateStatusMatch(match);
    }

    @Test
    void showActiveMatches() {
        // Arrange
        List<Meciuri> activeMatches = new ArrayList<>();
        when(meciuriService.getMatchesByStatus("active")).thenReturn(activeMatches);

        // Act
        String viewName = meciuriController.showActiveMatches(model);

        // Assert
        assertEquals("meciuri/activeMeciuri", viewName);
        verify(model, times(1)).addAttribute("meciuri", activeMatches);
    }


    @Test
    void viewMatchesByCompetition() {
        // Arrange
        Long competitionId = 1L;
        List<CompetitiiMain> competitions = new ArrayList<>();
        List<Meciuri> matches = new ArrayList<>();
        when(competitiiMainService.getAllCompetitions()).thenReturn(competitions);
        when(meciuriService.getMatchesAndCompetitions(competitionId)).thenReturn(matches);

        // Act
        String viewName = meciuriController.viewMatches(competitionId, model);

        // Assert
        assertEquals("meciuri/view-meciuri-competition", viewName);
        verify(model, times(1)).addAttribute("competitions", competitions);
        verify(model, times(1)).addAttribute("meciuri", matches);
    }
}
