package com.tournament.fifa_tournament.repository;

import com.tournament.fifa_tournament.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    Optional<Club> findByName(String name);
}