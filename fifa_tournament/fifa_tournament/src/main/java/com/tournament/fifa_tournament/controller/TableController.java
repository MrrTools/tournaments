package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;
import com.tournament.fifa_tournament.models.UserClass;
import com.tournament.fifa_tournament.security.CustomUserDetailsService;
import com.tournament.fifa_tournament.service.ClubService;
import com.tournament.fifa_tournament.service.LeagueTableService;
import com.tournament.fifa_tournament.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TableController {
    private final LeagueTableService leagueTableService;
    private final ClubService clubService;
    private final UserService userService;

    public TableController(LeagueTableService leagueTableService, ClubService clubService, UserService userService) {
        this.leagueTableService = leagueTableService;
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping("/table")
    public String LeagueTable(Model model){
        UserClass userClass = new UserClass();
        String userName = CustomUserDetailsService.getSessionUser();
        if(userName != null) {
            userClass = userService.findByUserName(userName);
            model.addAttribute("userClass", userClass);
        } else {
            model.addAttribute("userClass", userClass);
        }
        List<LeagueTableDTO> rows = leagueTableService.findAllRows();
        model.addAttribute("rows", rows);
        return "league_table";
    }

}