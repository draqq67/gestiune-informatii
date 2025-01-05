package com.dragos.gestiune_informatii.controller;

import com.dragos.gestiune_informatii.model.Echipe;
import com.dragos.gestiune_informatii.service.EchipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/echipe")
public class EchipeController {

    private final EchipeService echipeService;

    @Autowired
    public EchipeController(EchipeService echipeService) {
        this.echipeService = echipeService;
    }

    @GetMapping
    public List<Echipe> getAllTeams() {
        return echipeService.getAllTeams();
    }

    @GetMapping("/search")
    public List<Echipe> getTeamsByName(@RequestParam String name) {
        return echipeService.getTeamsByName(name);
    }

    @GetMapping("/year")
    public List<Echipe> getTeamsByYear(@RequestParam Integer year) {
        return echipeService.getTeamsByYear(year);
    }
}
