package com.tournament.fifa_tournament.repository;

import com.tournament.fifa_tournament.models.Club;
import com.tournament.fifa_tournament.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Integer> {
    Optional<Match> findByMatchID(Integer matchID);
}
