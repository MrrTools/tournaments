package com.tournament.fifa_tournament.matches;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.models.Match;
import com.tournament.fifa_tournament.service.ClubService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Recomputation {

    private ClubService clubService;

    public Recomputation(ClubService clubService) {
        this.clubService = clubService;
    }

    public void tableRecomputation(Match match){
        ClubDTO homeClub = clubService.findByName(match.getHome());
        ClubDTO awayClub = clubService.findByName(match.getAway());
        String result = match.getResult();
        int points;

        String[] splitResult = result.split(":");
        String prvaHodnota = splitResult[0];
        String druhaHodnota = splitResult[1];

        System.out.println("Test: " + prvaHodnota + druhaHodnota);

        if (prvaHodnota == druhaHodnota) {
            points = 1;
        }
        else {
            points = 3;
        }



            System.out.println("Test: " + homeClub + awayClub);


    }

}
