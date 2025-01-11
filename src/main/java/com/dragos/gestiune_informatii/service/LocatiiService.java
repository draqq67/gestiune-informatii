/**
 * Clasa pentru LocatiiService
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.Locatii;
import com.dragos.gestiune_informatii.repository.LocatiiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocatiiService {

    private final LocatiiRepository locatiiRepository;

    @Autowired
    public LocatiiService(LocatiiRepository locatiiRepository) {
        this.locatiiRepository = locatiiRepository;
    }

    public List<Locatii> getAllLocations() {
        return (List<Locatii>) locatiiRepository.findAllLocations();
    }
}
