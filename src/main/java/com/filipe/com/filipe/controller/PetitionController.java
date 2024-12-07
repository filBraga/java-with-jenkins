package com.filipe.com.filipe.controller;

import com.filipe.com.filipe.model.Petition;
import com.filipe.com.filipe.service.PetitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/petitions")
public class PetitionController {
    private final PetitionService petitionService;

    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    // Create
    @PostMapping
    public Petition createPetition(@RequestBody Petition petition) {
        return petitionService.createPetition(petition.getTitle(), petition.getDescription());
    }

    // Read all
    @GetMapping
    public List<Petition> getAllPetitions() {
        return petitionService.getAllPetitions();
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Petition> getPetitionById(@PathVariable Long id) {
        Petition petition = petitionService.getPetitionById(id);
        return petition != null ? ResponseEntity.ok(petition) : ResponseEntity.notFound().build();
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Petition> updatePetition(@PathVariable Long id, @RequestBody Petition petition) {
        Petition updatedPetition = petitionService.updatePetition(id, petition.getTitle(), petition.getDescription());
        return updatedPetition != null ? ResponseEntity.ok(updatedPetition) : ResponseEntity.notFound().build();
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetition(@PathVariable Long id) {
        boolean deleted = petitionService.deletePetition(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
