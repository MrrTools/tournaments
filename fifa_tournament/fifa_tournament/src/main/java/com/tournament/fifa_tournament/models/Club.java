package com.tournament.fifa_tournament.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data // nahradza gettry, settry, konstruktory atd
@Builder // pre vytvorenie objektov
@AllArgsConstructor // konstruktor s atributmi
@NoArgsConstructor  // konstruktory bez atributov
@Entity
@Table(name = "clubs", schema = "tournaments")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clubID;
    private String name;
    private String country;
    @CreationTimestamp
    private LocalDateTime createdDate;

    @OnDelete(action = OnDeleteAction.SET_NULL)
    @OneToOne(mappedBy = "club", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private LeagueTable leagueTable;

    @OnDelete(action = OnDeleteAction.SET_NULL)
    @OneToOne(mappedBy = "club", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
    //private Set<Player> players = new HashSet<>();
    private Player player;
}