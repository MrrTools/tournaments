package com.tournament.fifa_tournament.dataTransferObjects;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data // nahradza gettry, settry, konstruktory atd
@Builder // pre vytvorenie objektov
public class MatchDTO {
    private Integer round;
    private Integer matchID;
    private String home;
    private String result;
    private String away;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}