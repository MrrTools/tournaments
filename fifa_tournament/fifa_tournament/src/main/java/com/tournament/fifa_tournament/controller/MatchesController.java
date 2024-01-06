package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.MatchDTO;
import com.tournament.fifa_tournament.matches.MatchGenerator;
import com.tournament.fifa_tournament.matches.Recomputation;
import com.tournament.fifa_tournament.models.Match;
import com.tournament.fifa_tournament.models.UserClass;
import com.tournament.fifa_tournament.security.CustomUserDetailsService;
import com.tournament.fifa_tournament.service.MatchService;
import com.tournament.fifa_tournament.service.UserService;
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
    private UserService userService;

    @Autowired
    public MatchesController(MatchService matchService, MatchGenerator matchGenerator, Recomputation recomputation, UserService userService) {
        this.matchService = matchService;
        this.matchGenerator = matchGenerator;
        this.recomputation = recomputation;
        this.userService = userService;
    }

    @PostMapping("/generate")
    public String generate() {
        matchGenerator.generateMatches();
        return "redirect:/zapasy";
    }

    @GetMapping("/zapasy")
    public String listMatches(Model model) {
        UserClass userClass = new UserClass();
        String userName = CustomUserDetailsService.getSessionUser();
        if (userName != null) {
            userClass = userService.findByUserName(userName);
            model.addAttribute("userClass", userClass);
        }
        model.addAttribute("userClass", userClass);
        List<MatchDTO> matches = matchService.findAllMatches();
        model.addAttribute("matches", matches);
        return "zapasy";
    }

    @PostMapping("/update")
    public String editMatch(Match match) {
        match.setHome(matchService.findByMatchID(match.getMatchID()).getHome());
        match.setAway(matchService.findByMatchID(match.getMatchID()).getAway());
        match.setRound(matchService.findByMatchID(match.getMatchID()).getRound());
        match.setCreatedDate(matchService.findByMatchID(match.getMatchID()).getCreatedDate());
        matchService.saveMatch(match);
        recomputation.tableRecomputation(match);
        return "redirect:/zapasy";
    }
}