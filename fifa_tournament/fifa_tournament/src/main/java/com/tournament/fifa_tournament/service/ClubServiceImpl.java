package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.models.Club;
import com.tournament.fifa_tournament.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;

    @Autowired //označenie závislostí, ktoré by mali byť automaticky vložené do beany (objektu)
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public ClubDTO findByClubID(Integer clubID) {
        Club club = clubRepository.findById(clubID).get();
        return mapToClubDTO(club);
    }

    @Override
    public void deleteClub(Integer clubID) {
        clubRepository.deleteById(clubID);
    }

    public ClubDTO findByName(String name) {
        Club club = clubRepository.findByName(name).get();
        return mapToClubDTO(club);
    }

    @Override
    public List<ClubDTO> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map(club -> mapToClubDTO(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    private ClubDTO mapToClubDTO(Club club){
        ClubDTO clubDTO = ClubDTO.builder()
                .clubID(club.getClubID())
                .name(club.getName())
                .country(club.getCountry())
                .createdDate(club.getCreatedDate())
                .build();
        return clubDTO;
    }

}