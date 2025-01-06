package com.dragos.gestiune_informatii.service;

import com.dragos.gestiune_informatii.model.Organizatori;
import com.dragos.gestiune_informatii.repository.OrganizatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizatoriService {

    @Autowired
    private OrganizatoriRepository organizatoriRepository;


    public Organizatori saveOrganizer(Organizatori organizer) {
        return organizatoriRepository.save(organizer);
    }
}
