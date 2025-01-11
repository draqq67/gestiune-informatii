/**
 * Clasa pentru EchipeController
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.model.Echipe;
import com.dragos.gestiune_informatii.service.EchipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.List;

@Controller
public class EchipeController {

    private final EchipeService echipeService;

    @Autowired
    public EchipeController(EchipeService echipeService) {
        this.echipeService = echipeService;
    }

    @GetMapping("echipe/search")
    public List<Echipe> getTeamsByName(@RequestParam String name) {
        return echipeService.getTeamsByName(name);
    }

    @GetMapping("echipe/year")
    public List<Echipe> getTeamsByYear(@RequestParam Integer year) {
        return echipeService.getTeamsByYear(year);
    }
    @GetMapping("echipe/{id}")
    public String getTeamDetails(@PathVariable Integer id, Model model) {
        Echipe echipa = echipeService.findById(id);
        model.addAttribute("echipa", echipa);
        return "echipaDetails";
    }
}
