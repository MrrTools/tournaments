package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAllClubs();
}
