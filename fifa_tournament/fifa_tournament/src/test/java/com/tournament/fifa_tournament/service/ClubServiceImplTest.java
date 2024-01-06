package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.ClubDTO;
import com.tournament.fifa_tournament.models.Club;
import com.tournament.fifa_tournament.repository.ClubRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClubServiceImplTest {

    @Mock // falosny testovaci objekt
    private ClubRepository clubRepository;

    @InjectMocks // vlozenie clubRepository do service
    private ClubServiceImpl clubService;

    @BeforeEach  // priprava objektov pred kazdym testom
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByClubID() {
        Club club = new Club();
        club.setClubID(1);
        club.setName("Manchester United");
        club.setCountry("Eng");
        LocalDateTime date = LocalDateTime.now();
        club.setCreatedDate(date);

        // Mockovanie volaní metód pre ClubRepository
        when(clubRepository.findById(1)).thenReturn(Optional.of(club));

        ClubDTO result = clubService.findByClubID(1);

        // Overenie hodnot
        assertEquals(1, result.getClubID());
        assertEquals("Manchester United", result.getName());
        assertEquals("Eng", result.getCountry());
        assertEquals(date, result.getCreatedDate());

        // Overenie volaní metód pre ClubRepository
        verify(clubRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteClub() {
        clubService.deleteClub(1);
        verify(clubRepository, times(1)).deleteById(1);
    }

    @Test
    void testFindByName() {
        Club club = new Club();
        club.setClubID(1);
        club.setName("Manchester United");
        club.setCountry("Eng");
        LocalDateTime date = LocalDateTime.now();
        club.setCreatedDate(date);

        when(clubRepository.findByName("Manchester United")).thenReturn(Optional.of(club));

        ClubDTO result = clubService.findByName("Manchester United");

        // Overenie hodnot
        assertEquals(1, result.getClubID());
        assertEquals("Manchester United", result.getName());
        assertEquals("Eng", result.getCountry());
        assertEquals(date, result.getCreatedDate());

        // Overenie volaní metód pre ClubRepository
        verify(clubRepository, times(1)).findByName("Manchester United");
    }

    @Test
    void testFindAllClubs() {
        // Mockovanie objektov Club pre test
        Club club1 = new Club();
        club1.setClubID(1);
        club1.setName("Manchester United");
        club1.setCountry("Eng");
        LocalDateTime date = LocalDateTime.now();
        club1.setCreatedDate(date);

        Club club2 = new Club();
        club2.setClubID(2);
        club2.setName("Chelsea");
        club2.setCountry("Eng");
        club2.setCreatedDate(date);

        List<Club> clubs = Arrays.asList(club1, club2);

        when(clubRepository.findAll()).thenReturn(clubs);

        List<ClubDTO> result = clubService.findAllClubs();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getClubID());
        assertEquals("Manchester United", result.get(0).getName());
        assertEquals("Eng", result.get(0).getCountry());
        assertEquals(date, result.get(0).getCreatedDate());
        assertEquals(2, result.get(1).getClubID());
        assertEquals("Chelsea", result.get(1).getName());
        assertEquals("Eng", result.get(1).getCountry());
        assertEquals(date, result.get(1).getCreatedDate());

        verify(clubRepository, times(1)).findAll();
    }

    @Test
    void testSaveClub() {
        Club club = new Club();
        club.setClubID(1);
        club.setName("Manchester United");
        club.setCountry("Eng");
        LocalDateTime date = LocalDateTime.now();
        club.setCreatedDate(date);

        when(clubRepository.save(club)).thenReturn(club);

        Club result = clubService.saveClub(club);

        assertEquals(club.getClubID(), result.getClubID());
        assertEquals(club.getName(), result.getName());
        assertEquals(club.getCountry(), result.getCountry());
        assertEquals(club.getCreatedDate(), result.getCreatedDate());

        verify(clubRepository, times(1)).save(club);

    }
}