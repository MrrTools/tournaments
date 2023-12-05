package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.dataTransferObjects.LeagueTableDTO;
import com.tournament.fifa_tournament.models.LeagueTable;
import com.tournament.fifa_tournament.repository.LeagueTableRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;

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
        //List<LeagueTable> leagueTables = leagueTableRepo.findAll(Sort.by("points").descending());
        List<LeagueTable> leagueTables = leagueTableRepo.findAll();
        leagueTables.sort(new LeagueTableComparator());
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
    public void updateTable(LeagueTable leagueTable) {
        entityManager.createQuery("UPDATE LeagueTable SET goals = :goals, points = :points WHERE rowID = :rowID")
                .setParameter("goals", leagueTable.getGoals())
                .setParameter("points", leagueTable.getPoints())
                .setParameter("rowID", leagueTable.getRowID())
                .executeUpdate();
    }

    private class LeagueTableComparator implements Comparator<LeagueTable> {
        @Override
        public int compare(LeagueTable team1, LeagueTable team2) {
            // Porovnaj bodové rozdiely
            // Vysledok porovnania -1 prve cislo mensia 0 rovne 1 prve cislo vacsie
            int pointsComparison = Integer.compare(team2.getPoints(), team1.getPoints());

            // Ak majú rovnaké body, porovnaj skóre
            if (pointsComparison == 0) {
                return compareScores(team1, team2);
            }

            return pointsComparison;
        }

        private int compareScores(LeagueTable team1, LeagueTable team2) {
            // porovnávanie skóre
            String[] score1Parts = team1.getGoals().split(":");
            String[] score2Parts = team2.getGoals().split(":");

            int goals1 = Integer.parseInt(score1Parts[0]);
            int goals2 = Integer.parseInt(score2Parts[0]);

            int difference = goals2 - goals1;

            return Integer.compare(difference, 0);
        }
    }

}
