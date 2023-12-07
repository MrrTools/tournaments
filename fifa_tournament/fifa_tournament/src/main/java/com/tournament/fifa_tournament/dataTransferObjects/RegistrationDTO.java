package com.tournament.fifa_tournament.dataTransferObjects;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // nahradza gettry, settry, konstruktory atd
@Builder // pre vytvorenie objektov
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private Integer userID;
    @Nonnull
    private String userName;
    @Nonnull
    private String password;
    @Nonnull
    private String role;

}
