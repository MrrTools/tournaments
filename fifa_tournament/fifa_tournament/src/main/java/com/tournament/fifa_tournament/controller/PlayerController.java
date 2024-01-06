package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;
import com.tournament.fifa_tournament.dataTransferObjects.PlayerDTO;
import com.tournament.fifa_tournament.models.Club;
import com.tournament.fifa_tournament.models.LeagueTable;
import com.tournament.fifa_tournament.models.Player;
import com.tournament.fifa_tournament.models.UserClass;
import com.tournament.fifa_tournament.security.CustomUserDetailsService;
import com.tournament.fifa_tournament.service.ClubService;
import com.tournament.fifa_tournament.service.LeagueTableService;
import com.tournament.fifa_tournament.service.PlayerService;
import com.tournament.fifa_tournament.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PlayerController {
    private PlayerService playerService;
    private ClubService clubService;
    private UserService userService;
    private LeagueTableService leagueTableService;

    @Autowired //označenie závislostí, ktoré by mali byť automaticky vložené do beany (objektu)
    public PlayerController(PlayerService playerService, ClubService clubService, UserService userService, LeagueTableService leagueTableService) {
        this.playerService = playerService;
        this.clubService = clubService;
        this.userService = userService;
        this.leagueTableService = leagueTableService;
    }

    @GetMapping("/players")
    public String listClubs(Model model) {
        UserClass userClass = new UserClass();
        String userName = CustomUserDetailsService.getSessionUser();
        if (userName != null) {
            userClass = userService.findByUserName(userName);
            model.addAttribute("userClass", userClass);
        }
        model.addAttribute("userClass", userClass);
        List<PlayerDTO> players = playerService.findAllPlayers();
        model.addAttribute("players", players);
        List<ClubDTO> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "players";
    }

    @GetMapping("/players/delete/{playerID}")
    public String deletePlayer(@PathVariable("playerID") Integer playerID) {
        playerService.deletePlayer(playerID);
        return "redirect:/players";
    }

    @PostMapping("/players")
    public String savePlayer(Player player, ClubDTO clubDTO, LeagueTable leagueTable) {
        List<LeagueTableDTO> leagueTableDTO = leagueTableService.findAllRows();
        Club clubReference = new Club();
        clubReference.setClubID(clubDTO.getClubID());
        player.setClub(clubReference);
        playerService.savePlayer(player);
        boolean clubExists = false;
        for (LeagueTableDTO record : leagueTableDTO) {
            if (clubReference != null && clubReference.getClubID().equals(record.getClub().getClubID())) {
                clubExists = true;
                break;
            }
        }
        if (clubExists == false) {
            leagueTable.setClub(clubReference);
            leagueTableService.createClubInTable(leagueTable);
        }
        return "redirect:/players";
    }

}