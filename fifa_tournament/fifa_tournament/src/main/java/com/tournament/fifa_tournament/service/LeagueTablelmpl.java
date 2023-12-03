package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;
import com.tournament.fifa_tournament.models.LeagueTable;
import com.tournament.fifa_tournament.repository.LeagueTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeagueTablelmpl implements LeagueTableService {
    private LeagueTableRepository leagueTableRepo;

    @Autowired //označenie závislostí, ktoré by mali byť automaticky vložené do beany (objektu)
    public LeagueTablelmpl(LeagueTableRepository leagueTableRepo) {
        this.leagueTableRepo = leagueTableRepo;
    }


    @Override
    public List<LeagueTableDTO> findAllRows() {
        List<LeagueTable> leagueTables = leagueTableRepo.findAll(Sort.by("points").descending());
        return leagueTables.stream().map(leagueTable -> mapToLeagueTableDTO(leagueTable)).collect(Collectors.toList());
    }

    private LeagueTableDTO mapToLeagueTableDTO(LeagueTable leagueTable){
        LeagueTableDTO leagueTableDTO = LeagueTableDTO.builder()
                .rowID(leagueTable.getRowID())
                .goals(leagueTable.getGoals())
                .points(leagueTable.getPoints())
                .club(ClubDTO.builder().name(leagueTable.getClub().getName()).build())
                .createdDate(leagueTable.getCreatedDate())
                .updatedDate(leagueTable.getUpdatedDate())
                .build();
        return leagueTableDTO;
    }

}
