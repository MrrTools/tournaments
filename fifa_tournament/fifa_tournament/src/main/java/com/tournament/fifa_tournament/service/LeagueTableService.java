package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;
import com.tournament.fifa_tournament.models.LeagueTable;

import java.util.List;

public interface LeagueTableService {

    LeagueTable createClubInTable(LeagueTable leagueTable);

    List<LeagueTableDTO> findAllRows();

    LeagueTableDTO findByClubClubID(Integer clubID);

    void updateTable(LeagueTable leagueTable);

    void deleteClubTable(Integer clubID);

}
