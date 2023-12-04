package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;
import com.tournament.fifa_tournament.models.LeagueTable;
import com.tournament.fifa_tournament.repository.LeagueTableRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LeagueTablelmpl implements LeagueTableService {
    private LeagueTableRepository leagueTableRepo;

    @PersistenceContext
    private EntityManager entityManager;

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

    public LeagueTableDTO findByClubClubID(Integer clubID) {
        LeagueTable leagueTable = leagueTableRepo.findByClubClubID(clubID).get();
        return mapToLeagueTableDTO(leagueTable);
    }

    @Override
    @Transactional
    public void saveTable(LeagueTable leagueTable) {
        entityManager.createQuery("UPDATE LeagueTable SET goals = :goals, points = :points WHERE rowID = :rowID")
                .setParameter("goals", leagueTable.getGoals())
                .setParameter("points", leagueTable.getPoints())
                .setParameter("rowID", leagueTable.getRowID())
                .executeUpdate();
    }

}
