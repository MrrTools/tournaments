package com.tournament.fifa_tournament.matches;

import com.tournament.fifa_tournament.models.LeagueTable;
import com.tournament.fifa_tournament.models.Match;
import com.tournament.fifa_tournament.service.ClubService;
import com.tournament.fifa_tournament.service.LeagueTableService;
import org.springframework.stereotype.Component;

//refaktorovat triedu pre zjednoduenie kodu na konci

@Component
public class Recomputation {

    private ClubService clubService;
    private LeagueTableService leagueTableService;

    public Recomputation(ClubService clubService, LeagueTableService leagueTableService) {
        this.clubService = clubService;
        this.leagueTableService = leagueTableService;
    }

    private void updateTable(Match match, String team, int goalsFor, int goalsAgainst) {
        LeagueTable leagueTable = new LeagueTable();
        leagueTable.setRowID(leagueTableService.findByClubClubID(clubService.findByName(team).getClubID()).getRowID());

        int points = 0;
        if (!"BYE".equals(match.getHome()) && !"BYE".equals(match.getAway())) {
            int homeGoals = Integer.parseInt(match.getResult().split(":")[0]);
            int awayGoals = Integer.parseInt(match.getResult().split(":")[1]);

            if (team.equals(match.getHome())) {
                if (homeGoals > awayGoals) {
                    points = 3;
                } else if (homeGoals == awayGoals) {
                    points = 1;
                }
            } else if (team.equals(match.getAway())) {
                if (awayGoals > homeGoals) {
                    points = 3;
                } else if (awayGoals == homeGoals) {
                    points = 1;
                }
            }
        }

        leagueTable.setPoints(leagueTableService.findByClubClubID(clubService.findByName(team).getClubID()).getPoints() + points);

        String currentScore = leagueTableService.findByClubClubID(clubService.findByName(team).getClubID()).getGoals();
        String[] splitScore = currentScore.split(":");
        String newScore = String.format("%d:%d", Integer.parseInt(splitScore[0]) + goalsFor, Integer.parseInt(splitScore[1]) + goalsAgainst);
        leagueTable.setGoals(newScore);

        leagueTableService.updateTable(leagueTable);
    }

    public void tableRecomputation(Match match) {
        if (!"BYE".equals(match.getHome())) {
            updateTable(match, match.getHome(), Integer.parseInt(match.getResult().split(":")[0]), Integer.parseInt(match.getResult().split(":")[1]));
        }

        if (!"BYE".equals(match.getAway())) {
            updateTable(match, match.getAway(), Integer.parseInt(match.getResult().split(":")[1]), Integer.parseInt(match.getResult().split(":")[0]));
        }
    }
}

