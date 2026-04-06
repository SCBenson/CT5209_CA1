package com.ct5209.seanspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Controller
public class PetitionController {

    private static final List<Petition> petitions = new ArrayList<>();
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    static {
        Petition p1 = new Petition(idCounter.getAndIncrement(),
                "Save the Local Park",
                "We need to save our local park from being turned into a shopping mall.");
        p1.addSignature(new Signature("Mark Stiller", "mark@example.com"));
        p1.addSignature(new Signature("Joanne Murphy", "joanne@example.com"));

        Petition p2 = new Petition(idCounter.getAndIncrement(),
                "More Bike Lanes Downtown",
                "Requesting more bike lanes in the downtown area to promote eco-friendly transportation.");
        p2.addSignature(new Signature("Emily Davis", "emily@example.com"));

        Petition p3 = new Petition(idCounter.getAndIncrement(),
                "Extend Library Hours",
                "Students and workers need the public library open until 10pm on weekdays.");

        petitions.add(p1);
        petitions.add(p2);
        petitions.add(p3);
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("petitions", petitions);
        return "index";
    }

    @GetMapping("/create")
    public String createForm(){
        return "create";
    }

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description){
        Petition p = new Petition(idCounter.getAndIncrement(), title, description);
        petitions.add(p);
        return "redirect:/";
    }

    @GetMapping("/petition/{id}")
    public String viewPetition(@PathVariable int id, Model model){
        Petition petition = petitions.stream()
                .filter(p-> p.getId() == id)
                .findFirst()
                .orElse(null);
        if(petition == null) return "redirect:/";
        model.addAttribute("petition", petition);
        return "petition";
    }

    @PostMapping("/petition/{id}/sign")
    public String signPetition(@PathVariable int id, @RequestParam String name, @RequestParam String email){
        petitions.stream()
                .filter(p-> p.getId() == id)
                .findFirst()
                .ifPresent(p-> p.addSignature(new Signature(name, email)));
        return "redirect:/petition/" + id;
    }

    @GetMapping("/search")
    public String searchForm(){
        return "search";
    }

    @GetMapping("/search/results")
    public String searchResults(@RequestParam String query, Model model){
        List<Petition> results = petitions.stream()
                .filter(p-> p.getTitle().toLowerCase().contains(query.toLowerCase())
                || p.getDescription().toLowerCase().contains(query.toLowerCase()))
                        .collect(Collectors.toList());
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        return "search-results";
    }

}
