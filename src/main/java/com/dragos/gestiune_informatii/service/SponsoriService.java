/**
 * Clasa pentru SponsoriService
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.Sponsori;
import com.dragos.gestiune_informatii.repository.SponsoriRepository;
import com.dragos.gestiune_informatii.repository.SponsoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SponsoriService {

    @Autowired
    private SponsoriRepository sponsoriiRepository;

    // Method to add a new sponsor
    public Sponsori addSponsor(Sponsori sponsor) {
        return sponsoriiRepository.save(sponsor); // Saves the new sponsor
    }

    // Method to update an existing sponsor

}
