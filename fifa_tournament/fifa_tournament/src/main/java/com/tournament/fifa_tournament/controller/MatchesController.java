package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.MatchDTO;
import com.tournament.fifa_tournament.matches.MatchGenerator;
import com.tournament.fifa_tournament.matches.Recomputation;
import com.tournament.fifa_tournament.models.Match;
import com.tournament.fifa_tournament.service.ClubService;
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
    private final Recomputation recomputation;

    @Autowired
    public MatchesController(MatchService matchService, MatchGenerator matchGenerator, Recomputation recomputation) {
        this.matchService = matchService;
        this.matchGenerator = matchGenerator;
        this.recomputation = recomputation;
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

    @PostMapping( "/update")
    public String editMatch(Match match) {

        //nefunguje @JsonInclude(JsonInclude.Include.NON_NULL), neviem spravne pouzit ?
        match.setHome(matchService.findByMatchID(match.getMatchID()).getHome());
        match.setAway(matchService.findByMatchID(match.getMatchID()).getAway());
        match.setRound(matchService.findByMatchID(match.getMatchID()).getRound());
        match.setCreatedDate(matchService.findByMatchID(match.getMatchID()).getCreatedDate());
        matchService.saveMatch(match);
        recomputation.tableRecomputation(match);
        return "redirect:/zapasy";
    }

}