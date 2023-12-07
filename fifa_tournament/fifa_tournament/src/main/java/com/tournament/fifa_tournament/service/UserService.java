package com.tournament.fifa_tournament.service;

import com.tournament.fifa_tournament.dataTransferObjects.RegistrationDTO;
import com.tournament.fifa_tournament.models.UserClass;

public interface UserService  {
    void  saveUser (RegistrationDTO registrationDTO);

    UserClass findByUserName(String userName);
}
