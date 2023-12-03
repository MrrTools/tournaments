package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;
import com.tournament.fifa_tournament.service.ClubService;
import com.tournament.fifa_tournament.service.LeagueTableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TableController {
    private final LeagueTableService leagueTableService;
    private final ClubService clubService;

    public TableController(LeagueTableService leagueTableService, ClubService clubService) {
        this.leagueTableService = leagueTableService;
        this.clubService = clubService;
    }

    @GetMapping("/table")
    public String LeagueTable(Model model){
        List<LeagueTableDTO> rows = leagueTableService.findAllRows();
        model.addAttribute("rows", rows);
        return "league_table";
    }

}
