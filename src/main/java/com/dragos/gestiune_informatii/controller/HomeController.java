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
        pages.put("Adauga Competitii", "/competitii/adauga");
        pages.put("Vezi Competitiile", "/competitii");
        pages.put("Vezi meciurile", "/meciuri");
        pages.put("Vezi meciurile dintr-o competitie", "/meciuri/competitii");
        pages.put("Vezi meciurile dintr-o locatie", "/GasesteDupaLocatie" );
        pages.put("Vezi participantii din fiecare competitie","/competitii/participanti");
        pages.put("Sterge Competitiile si datele in legatura cu ea","/competitii/sterge");
//        pages.put("Add Organizer", "/add-organizer");
//        pages.put("View Organizers", "/view-organizers");

        model.addAttribute("pages", pages);
        model.addAttribute("projectDescription", "Acest proiect este o platformă destinată gestionării informațiilor despre competiții, organizatori și alte aspecte conexe. Utilizatorii au posibilitatea de a adăuga, actualiza și vizualiza datele într-un mod intuitiv. Platforma este concepută pentru a simplifica procesul de administrare a evenimentelor și pentru a oferi o experiență facilă tuturor utilizatorilor.\n" +
                "\n" +
                "Prin intermediul acestei aplicații, organizatorii pot centraliza informațiile, pot urmări progresul competițiilor și pot colabora eficient cu alte părți implicate. De asemenea, utilizatorii beneficiază de o interfață prietenoasă și de funcționalități care le permit să acceseze rapid informațiile necesare.");
        model.addAttribute("githubLink", "https://github.com/draqq67/gestiune-informatii");

        return "home";
    }
}
