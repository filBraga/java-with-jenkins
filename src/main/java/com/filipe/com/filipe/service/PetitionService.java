package com.filipe.com.filipe.service;

import com.filipe.com.filipe.model.Petition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PetitionService {
    private final List<Petition> petitions = new ArrayList<>();
    private final Map<Long, List<String>> petitionSignatures = new HashMap<>();

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

    public Petition createPetition(String title, String description) {
        Petition petition = new Petition((long) (petitions.size() + 1), title, description, new ArrayList<>());
        petitions.add(petition);
        petitionSignatures.put(petition.getId(), new ArrayList<>());
        return petition;
    }

    public Petition getPetitionById(Long id) {
        return petitions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void signPetition(Long id, String name, String email) {
        petitionSignatures.get(id).add(name + " (" + email + ")");
    }

    public List<Petition> getAllPetitions() {
        return petitions;
    }
}
