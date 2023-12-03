package com.tournament.fifa_tournament.repository;

import com.tournament.fifa_tournament.models.Match;
import com.tournament.fifa_tournament.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findByPlayerID(Integer playerID);
}
