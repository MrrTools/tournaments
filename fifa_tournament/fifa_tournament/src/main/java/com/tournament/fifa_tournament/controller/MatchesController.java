package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.dataTransferObjects.MatchDTO;
import com.tournament.fifa_tournament.matches.MatchGenerator;
import com.tournament.fifa_tournament.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MatchesController {

    private final MatchService matchService;
    private final MatchGenerator matchGenerator;

    @Autowired
    public MatchesController(MatchService matchService, MatchGenerator matchGenerator) {
        this.matchService = matchService;
        this.matchGenerator = matchGenerator;
    }

    @PostMapping("/generate")
    public String generate() {
        matchGenerator.generateMatches();
        return "redirect:/zapasy";
    }

    @GetMapping("/zapasy")
    public String listMatches(Model model) {
        List<MatchDTO> matches = matchService.findAllMatches();
        model.addAttribute("matches", matches);
        return "zapasy";
    }

}