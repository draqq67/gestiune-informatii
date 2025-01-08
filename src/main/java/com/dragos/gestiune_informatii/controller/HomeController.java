package com.dragos.gestiune_informatii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // Map of page names and their respective URLs
        Map<String, String> pages = new LinkedHashMap<>();
        pages.put("Add Competition", "/competitions/add");
        pages.put("View Competitions", "/competitions");
        pages.put("View meciuri", "/meciuri");
        pages.put("View meciuri competitions", "/meciuri/competition");
        pages.put("see meciuri on each location", "/findByLocation" );
        pages.put("see participants from every competition","/competitions/participants");
        pages.put("delete competitii and related data","/competitions/delete");
//        pages.put("Add Organizer", "/add-organizer");
//        pages.put("View Organizers", "/view-organizers");

        model.addAttribute("pages", pages);
        model.addAttribute("projectDescription", "This project is a platform for managing information about competitions, organizers, and more. It allows users to add, update, and view data intuitively.");
        model.addAttribute("githubLink", "https://github.com/draqq67/gestiune-informatii");

        return "home";
    }
}
