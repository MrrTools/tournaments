package com.tournament.fifa_tournament.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


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

    public Club(String name, String country, Integer clubID, LocalDateTime createdDate) {
        this.name = name;
        this.country = country;
        this.clubID = clubID;
        this.createdDate = createdDate;
    }

    public Club() {
    }

    public void setClubID(Integer clubID) {
        this.clubID = clubID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Integer getClubID() {
        return clubID;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

}


