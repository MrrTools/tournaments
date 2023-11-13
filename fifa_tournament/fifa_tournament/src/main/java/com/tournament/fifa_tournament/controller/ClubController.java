package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.models.Club;
import com.tournament.fifa_tournament.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    @Autowired //označenie závislostí, ktoré by mali byť automaticky vložené do beany (objektu)
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
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
    public String deleteClub(@PathVariable("clubID") Long clubID) {
        clubService.deleteClub(clubID);
        return "redirect:/clubs";
    }

    @GetMapping("club/update/{clubID}")
    public String editClub(@PathVariable("clubID") long clubID, Model model) {
        ClubDTO club = clubService.findByClubID(clubID);
        model.addAttribute( "club", club);
        return "clubs";
    }

}


