package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;

import java.util.List;

public interface LeagueTableService {

    List<LeagueTableDTO> findAllRows();

}
