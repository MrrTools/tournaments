package com.tournament.fifa_tournament.matches;

import com.tournament.fifa_tournament.dataTransferObjects.PlayerDTO;
import com.tournament.fifa_tournament.models.Match;
import com.tournament.fifa_tournament.service.MatchService;
import com.tournament.fifa_tournament.service.PlayerService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchGenerator {
    
    private MatchService matchService;
    private PlayerService playerService;

    public MatchGenerator(MatchService matchService, PlayerService playerService) {
        this.matchService = matchService;
        this.playerService = playerService;
    }

    public void generateMatches() {


        List<String> teams = new ArrayList<>();
        List<PlayerDTO> allPlayers = playerService.findAllPlayers();
        int numberOfTeams = playerService.countPlayers();

        for (PlayerDTO playerDTO : allPlayers) {
            teams.add(playerDTO.getClub().getName());
        }
        
        if (numberOfTeams % 2 != 0) {
            teams.add("BYE");
            numberOfTeams++;
        }

        for (int i = 1; i <= numberOfTeams - 1; i++) {
            for (int j = 0; j < numberOfTeams / 2; j++) {

                String homeTeam = teams.get(j);
                String awayTeam = teams.get(numberOfTeams - 1 - j);

                Match match = new Match();
                match.setHome(homeTeam);
                match.setAway(awayTeam);
                match.setRound(i);

                matchService.saveMatch(match);

                //odvetne zapasy sucasne zapisane do DB v tabulke order by round
                match = new Match();
                match.setHome(awayTeam);
                match.setAway(homeTeam);
                match.setRound(numberOfTeams - 1 + i);

                matchService.saveMatch(match);

            }

            teams.add(1, teams.remove(teams.size() - 1));
        }
    }
}