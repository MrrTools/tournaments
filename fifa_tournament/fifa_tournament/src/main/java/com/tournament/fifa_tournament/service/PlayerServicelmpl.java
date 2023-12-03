package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.dataTransferObjects.PlayerDTO;
import com.tournament.fifa_tournament.models.Player;
import com.tournament.fifa_tournament.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServicelmpl implements PlayerService{
    private PlayerRepository playerRepository;

    @Autowired //označenie závislostí, ktoré by mali byť automaticky vložené do beany (objektu)
    public PlayerServicelmpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerDTO> findAllPlayers () {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(player -> mapToPlayerDTO(player)).collect(Collectors.toList());
    }

    private PlayerDTO mapToPlayerDTO(Player player){
        PlayerDTO playerDTO = PlayerDTO.builder()
                .playerID(player.getPlayerID())
                .name(player.getName())
                .surname(player.getSurname())
                .club(ClubDTO.builder().name(player.getClub().getName()).build())
                .first_season(player.getFirst_season())
                .trophies_number(player.getTrophies_number())
                .createdDate(player.getCreatedDate())
                .updatedDate(player.getUpdatedDate())
                .build();
        return playerDTO;
    }

    @Override
    public void deletePlayer(Integer playerID) {
        playerRepository.deleteById(playerID);
    }

    public int countPlayers() {
        return (int) playerRepository.count();
    }

}
