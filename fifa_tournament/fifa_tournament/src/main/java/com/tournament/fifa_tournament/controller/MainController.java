package com.tournament.fifa_tournament.controller;
import com.tournament.fifa_tournament.dataTransferObjects.MatchDTO;
import com.tournament.fifa_tournament.security.CustomUserDetailsService;
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
        String userName = CustomUserDetailsService.getSessionUser();
        model.addAttribute("username", userName);
        // Fetch the next matches
        List<MatchDTO> nextMatches = matchService.findNextMatches();
        model.addAttribute("nextMatches", nextMatches);

        return "index";
    }

    @GetMapping("/rules")
    public String rules(Model model) {
        String userName = CustomUserDetailsService.getSessionUser();
        model.addAttribute("username", userName);
        return "rules";
    }

    @GetMapping("/galery")
    public String galery(Model model) {
        String userName = CustomUserDetailsService.getSessionUser();
        model.addAttribute("username", userName);
        return "galery";
    }

    @GetMapping("/news")
    public String news(Model model) {
        String userName = CustomUserDetailsService.getSessionUser();
        model.addAttribute("username", userName);
        return "news";
    }
}