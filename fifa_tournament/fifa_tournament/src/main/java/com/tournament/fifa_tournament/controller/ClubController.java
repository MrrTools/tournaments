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
    public String listClubs(Model model){
        List<ClubDTO> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs";
    }

    @PostMapping("/clubs")
    public String saveClub(Club club){
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

   /* @GetMapping("/update/${clubID}")
    public String editClub(@RequestParam("clubID") Long clubID,
                           @RequestParam("newName")   String name,
                           @RequestParam("newCountry")   String country,
    ){
        Club club = new Club();
        club.setName(name);
        club.setCountry(country);
        clubService.findByClubID(club);
        return "redirect:/clubs"; */
    }


