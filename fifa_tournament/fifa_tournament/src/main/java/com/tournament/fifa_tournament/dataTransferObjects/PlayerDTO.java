package com.tournament.fifa_tournament.dataTransferObjects;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data // nahradza gettry, settry, konstruktory atd
@Builder // pre vytvorenie objektov
public class PlayerDTO {
    private Integer playerID;
    private String  name;
    private String  surname;
    private String first_season;
    private Integer trophies_number;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private ClubDTO club;
}