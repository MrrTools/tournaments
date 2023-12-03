package com.tournament.fifa_tournament.repository;

import com.tournament.fifa_tournament.models.LeagueTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeagueTableRepository extends JpaRepository<LeagueTable, Integer> {

    Optional<LeagueTable> findByRowID(Integer rowID);

}
