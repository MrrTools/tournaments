package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;
import com.tournament.fifa_tournament.models.Club;
import com.tournament.fifa_tournament.models.LeagueTable;

import java.util.List;

public interface LeagueTableService {

    List<LeagueTableDTO> findAllRows();

    LeagueTableDTO findByClubClubID(Integer clubID);

    void saveTable(LeagueTable leagueTable);

}
