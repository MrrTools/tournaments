package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.RegistrationDTO;
import com.tournament.fifa_tournament.models.UserClass;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void  saveUser (RegistrationDTO registrationDTO);

    UserClass findByUserName(String userName);
}
