package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.MatchDTO;
import com.tournament.fifa_tournament.models.Match;
import com.tournament.fifa_tournament.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<MatchDTO> findAllMatches() {
        List<Match> matches = matchRepository.findAll(Sort.by("round").ascending());
        return matches.stream().map(match -> mapToMatchDTO(match)).collect(Collectors.toList());
    }

    private MatchDTO mapToMatchDTO(Match match){
        MatchDTO matchDTO = MatchDTO.builder()
                .matchID(match.getMatchID())
                .round(match.getRound())
                .home(match.getHome())
                .result(match.getResult())
                .away(match.getAway())
                .build();
        return matchDTO;
    }


}
