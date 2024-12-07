package com.filipe.com.filipe.service;

import com.filipe.com.filipe.model.Petition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionService {
    private final List<Petition> petitions = new ArrayList<>();
    private Long nextId = 1L;

    // Create a petition
    public Petition createPetition(String title, String description) {
        Petition petition = new Petition(nextId++, title, description);
        petitions.add(petition);
        return petition;
    }

    // Read all petitions
    public List<Petition> getAllPetitions() {
        return petitions;
    }

    // Read a petition by ID
    public Petition getPetitionById(Long id) {
        return petitions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Update a petition
    public Petition updatePetition(Long id, String title, String description) {
        Petition petition = getPetitionById(id);
        if (petition != null) {
            petition.setTitle(title);
            petition.setDescription(description);
        }
        return petition;
    }

    // Delete a petition
    public boolean deletePetition(Long id) {
        return petitions.removeIf(p -> p.getId().equals(id));
    }
}
