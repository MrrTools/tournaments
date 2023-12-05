package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;
import com.tournament.fifa_tournament.dataTransferObjects.PlayerDTO;
import com.tournament.fifa_tournament.models.Club;
import com.tournament.fifa_tournament.models.Player;

import java.util.List;

public interface PlayerService {
    List<PlayerDTO> findAllPlayers();

    void deletePlayer(Integer playerID);

    int countPlayers();

    Player savePlayer(Player player);

}
