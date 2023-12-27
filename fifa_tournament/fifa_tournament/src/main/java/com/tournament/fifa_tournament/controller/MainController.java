package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.MatchDTO;
import com.tournament.fifa_tournament.service.MatchService;
import com.tournament.fifa_tournament.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final UserService userService;
    private final MatchService matchService;

    @Autowired
    public MainController(UserService userService, MatchService matchService) {
        this.userService = userService;
        this.matchService = matchService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        String currentUsername = userService.getCurrentUsername();
        model.addAttribute("username", currentUsername);

        // Fetch the next matches
        List<MatchDTO> nextMatches = matchService.findNextMatches();
        model.addAttribute("nextMatches", nextMatches);

        return "index";
    }

    @GetMapping("/rules")
    public String rules() {
        return "rules";
    }

    @GetMapping("/galery")
    public String galery() {
        return "galery";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }
}
