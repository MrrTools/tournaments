package com.tournament.fifa_tournament.dataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data // nahradza gettry, settry, konstruktory atd
@Builder // pre vytvorenie objektov
@AllArgsConstructor
@NoArgsConstructor
public class LeagueTableDTO {
    private Integer rowID;
    private String goals;
    private Integer points;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private ClubDTO club;
}