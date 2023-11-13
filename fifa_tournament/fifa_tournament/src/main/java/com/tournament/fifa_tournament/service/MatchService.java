package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.MatchDTO;
import com.tournament.fifa_tournament.models.Match;

import java.util.List;

public interface MatchService {
    Match saveMatch(Match match);

    List<MatchDTO> findAllMatches();

}
