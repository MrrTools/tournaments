package com.tournament.fifa_tournament.dataTransferObjects;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data // nahradza gettry, settry, konstruktory atd
@Builder // pre vytvorenie objektov
public class ClubDTO {
    private Integer clubID;
    private String name;
    private String country;
    private LocalDateTime createdDate;

}