package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.models.Club;
import com.tournament.fifa_tournament.models.LeagueTable;
import com.tournament.fifa_tournament.models.UserClass;
import com.tournament.fifa_tournament.security.CustomUserDetailsService;
import com.tournament.fifa_tournament.service.ClubService;
import com.tournament.fifa_tournament.service.LeagueTableService;
import com.tournament.fifa_tournament.service.PlayerService;
import com.tournament.fifa_tournament.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;
    private UserService userService;
    private LeagueTableService leagueTableService;
    private PlayerService playerService;

    @Autowired //označenie závislostí, ktoré by mali byť automaticky vložené do beany (objektu)
    public ClubController(ClubService clubService, UserService userService, LeagueTableService leagueTableService, PlayerService playerService) {
        this.clubService = clubService;
        this.userService = userService;
        this.leagueTableService = leagueTableService;
        this.playerService = playerService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        UserClass userClass = new UserClass();
        String userName = CustomUserDetailsService.getSessionUser();
        if(userName != null) {
            userClass = userService.findByUserName(userName);
            model.addAttribute("userClass", userClass);
        }
        model.addAttribute("userClass", userClass);
        List<ClubDTO> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs";
    }

    @PostMapping("/clubs")
    public String saveClub(Club club) {
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/club/delete/{clubID}")
    public String deleteClub(@PathVariable("clubID") Integer clubID) {
        //leagueTableService.deleteClubTable(clubID);
       // playerService.deletePlayer();
        clubService.deleteClub(clubID);
        return "redirect:/clubs";
    }

    @GetMapping("club/update/{clubID}")
    public String editClub(@PathVariable("clubID") Integer clubID, Model model) {
        ClubDTO club = clubService.findByClubID(clubID);
        model.addAttribute( "club", club);
        return "clubs";
    }

}