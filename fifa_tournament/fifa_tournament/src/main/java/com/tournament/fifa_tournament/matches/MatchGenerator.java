package com.tournament.fifa_tournament.matches;


import com.tournament.fifa_tournament.service.MatchService;

import java.util.ArrayList;
import java.util.List;

public class MatchGenerator {

    private int numberOfTeams;
    private MatchService matchService;

    public MatchGenerator(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public List<String> generateRoundRobinTournament() {
        List<String> matches = new ArrayList<>();
        List<String> teams = new ArrayList<>();

        // Naplňte seznam týmů názvy nebo identifikátory
        for (int i = 1; i <= numberOfTeams; i++) {
            teams.add("Team " + i);
        }

        // Algoritmus turnaje Round Robin s odvety
        for (int i = 0; i < numberOfTeams - 1; i++) {
            matches.add("\nRound " + (i + 1) + ":");

            for (int j = 0; j < numberOfTeams / 2; j++) {
                String team1 = teams.get(j);
                String team2 = teams.get(numberOfTeams - 1 - j);

                if (j % 2 == 1 || i % 2 == 1) {
                    matches.add(team2 + " vs. " + team1);
                    //matchService.saveMatch();
                } else {
                    matches.add(team1 + " vs. " + team2);
                }
            }

            // Rotace týmů
            teams.add(1, teams.remove(teams.size() - 1));
        }

        return matches;
    }
}





