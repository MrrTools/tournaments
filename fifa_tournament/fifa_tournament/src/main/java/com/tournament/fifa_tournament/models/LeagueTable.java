package com.tournament.fifa_tournament.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data // nahradza gettry, settry, konstruktory atd
@Builder // pre vytvorenie objektov
@AllArgsConstructor // konstruktor s atributmi
@NoArgsConstructor  // konstruktory bez atributov
@Entity
@Table(name = "league_table", schema = "tournaments")
public class LeagueTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rowID;
    private String goals;
    private Integer points;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;


    @OneToOne
    @JoinColumn(name = "clubID", nullable = false)
    private Club club;

}