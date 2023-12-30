package com.tournament.fifa_tournament.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data // nahradza gettry, settry, konstruktory atd
@Builder // pre vytvorenie objektov
@AllArgsConstructor // konstruktor s atributmi
@NoArgsConstructor  // konstruktory bez atributov
@Entity
@Table(name = "players", schema = "tournaments")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerID;
    private String  name;
    private String  surname;
    private String first_season;
    private Integer trophies_number;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @OneToOne
    @JoinColumn(name = "clubID", nullable = false)
    private Club club;
}