package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAllClubs();

    Club saveClub(Club club);

    ClubDTO findByClubID(Integer clubID);

    ClubDTO findByName(String name);

    void deleteClub(Integer clubID);
}