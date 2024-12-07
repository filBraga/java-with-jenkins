package com.filipe.com.filipe.controller;

import com.filipe.com.filipe.model.Petition;
import com.filipe.com.filipe.service.PetitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  // Change from @RestController to @Controller
@RequestMapping("/petitions")  // Adjust URL mapping to match your views
public class PetitionController {
    private final PetitionService petitionService;

    public PetitionController(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    // Render the form to create a petition
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("petition", new Petition(null, "", ""));
        return "create-petition";  // Return the view name (Thymeleaf template)
    }

    // Handle the form submission to create a petition
    @PostMapping("/create")
    public String createPetition(@ModelAttribute Petition petition) {
        petitionService.createPetition(petition.getTitle(), petition.getDescription());
        return "redirect:/petitions/view";  // Redirect to the view page after creation
    }

    // Render the page to view/search petitions
    @GetMapping("/view")
    public String viewPetitions(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Petition> petitions;
        if (search != null && !search.isEmpty()) {
            // Filter petitions by title
            petitions = petitionService.getAllPetitions().stream()
                    .filter(p -> p.getTitle().toLowerCase().contains(search.toLowerCase()))
                    .toList();
        } else {
            petitions = petitionService.getAllPetitions();
        }
        model.addAttribute("petitions", petitions);
        model.addAttribute("search", search);
        return "view-petitions";  // Return the view name (Thymeleaf template)
    }
}
