package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.models.Club;
import com.tournament.fifa_tournament.models.Match;
import com.tournament.fifa_tournament.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {
    private MatchRepository matchRepository;

    @Autowired //označenie závislostí, ktoré by mali byť automaticky vložené do beany (objektu)
    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

}
