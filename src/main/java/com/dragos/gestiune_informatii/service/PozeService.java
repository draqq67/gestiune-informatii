package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.Poze;
import com.dragos.gestiune_informatii.repository.PozeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PozeService {

    @Autowired
    private PozeRepository pozeRepository;

    // Method to get all photos for a competition
    public List<Poze> getAllPoze() {
        return (List<Poze>) pozeRepository.findAll(); // Converts Iterable to List
    }

    // Method to get a photo by its ID
    public Poze getPozaById(Integer id) {
        Optional<Poze> optionalPoza = pozeRepository.findById(id);
        return optionalPoza.orElse(null); // Return the photo or null if not found
    }

    // Method to add a new photo
    public Poze addPoza(Poze poza) {
        return pozeRepository.save(poza); // Saves the new photo
    }

    // Method to update an existing photo
    public Poze updatePoza(Integer id, Poze newPozaData) {
        Optional<Poze> existingPoza = pozeRepository.findById(id);
        if (existingPoza.isPresent()) {
            Poze pozaToUpdate = existingPoza.get();
            pozaToUpdate.setUrl(newPozaData.getUrl());
            pozaToUpdate.setAltText(newPozaData.getAltText());
            return pozeRepository.save(pozaToUpdate); // Save the updated photo
        }
        return null; // Return null if the photo with the given ID doesn't exist
    }

    // Method to delete a photo by its ID
    public void deletePoza(Integer id) {
        pozeRepository.deleteById(id); // Deletes the photo by ID
    }
}
