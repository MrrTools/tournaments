package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.PlayerDTO;

import java.util.List;

public interface PlayerService {
    List<PlayerDTO> findAllPlayers();

    void deletePlayer(Integer playerID);

    int countPlayers();
}
