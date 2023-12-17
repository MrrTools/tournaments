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

    public void tableRecomputation(Match match){
        Integer homeClubPoints = leagueTableService.findByClubClubID(clubService.findByName(match.getHome()).getClubID()).getPoints() + 1;
        Integer awayClubPoints = leagueTableService.findByClubClubID(clubService.findByName(match.getAway()).getClubID()).getPoints() + 1;

        String result = match.getResult();
        String[] splitResult = result.split(":");
        Integer homeGoals = Integer.parseInt(splitResult[0]);
        Integer awayGoals = Integer.parseInt(splitResult[1]);

        String scoreHome = leagueTableService.findByClubClubID(clubService.findByName(match.getHome()).getClubID()).getGoals();
        String[] splitscoreHome = scoreHome.split(":");
        String newScore = String.format("%d:%d", Integer.parseInt(splitscoreHome[0]) + homeGoals, Integer.parseInt(splitscoreHome[1]) + awayGoals);

        String scoreAway = leagueTableService.findByClubClubID(clubService.findByName(match.getAway()).getClubID()).getGoals();
        String[] splitscoreAway = scoreAway.split(":");
        String newScore2 = String.format("%d:%d", Integer.parseInt(splitscoreAway[0]) + awayGoals, Integer.parseInt(splitscoreAway[1]) + homeGoals);

        if (homeGoals.equals(awayGoals)) {
            homeClubPoints = leagueTableService.findByClubClubID(clubService.findByName(match.getHome()).getClubID()).getPoints() + 1;
            awayClubPoints = leagueTableService.findByClubClubID(clubService.findByName(match.getAway()).getClubID()).getPoints() + 1;
        }
        else if (homeGoals.compareTo(awayGoals) > 0) {
            homeClubPoints = leagueTableService.findByClubClubID(clubService.findByName(match.getHome()).getClubID()).getPoints() + 3;
        }
        else {
            awayClubPoints = leagueTableService.findByClubClubID(clubService.findByName(match.getAway()).getClubID()).getPoints() + 3;
        }

        //zjednodusenie kodu v pripade @JsonInclude(JsonInclude.Include.NON_NULL)
        LeagueTable leagueTable = new LeagueTable();
        leagueTable.setPoints(homeClubPoints);
        leagueTable.setGoals(newScore);
        leagueTable.setRowID(leagueTableService.findByClubClubID(clubService.findByName(match.getHome()).getClubID()).getRowID());
        leagueTableService.updateTable(leagueTable);

        leagueTable.setPoints(awayClubPoints);
        leagueTable.setGoals(newScore2);
        leagueTable.setRowID(leagueTableService.findByClubClubID(clubService.findByName(match.getAway()).getClubID()).getRowID());
        leagueTableService.updateTable(leagueTable);

    }

}
