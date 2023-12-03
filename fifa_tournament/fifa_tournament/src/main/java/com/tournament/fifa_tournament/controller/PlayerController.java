package com.tournament.fifa_tournament.controller;

import com.tournament.fifa_tournament.dataTransferObjects.PlayerDTO;
import com.tournament.fifa_tournament.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PlayerController {
    private PlayerService playerService;

    @Autowired //označenie závislostí, ktoré by mali byť automaticky vložené do beany (objektu)
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public String listClubs(Model model) {
        List<PlayerDTO> players = playerService.findAllPlayers();
        model.addAttribute("players", players);
        return "players";
    }

    @GetMapping("/players/delete/{playerID}")
    public String deletePlayer(@PathVariable("playerID") Integer playerID) {
        playerService.deletePlayer(playerID);
        return "redirect:/players";
    }
}
