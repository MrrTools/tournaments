package com.tournament.fifa_tournament.service;


import com.tournament.fifa_tournament.dataTransferObjects.RegistrationDTO;
import com.tournament.fifa_tournament.models.UserClass;
import com.tournament.fifa_tournament.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDTO registrationDto) {
        UserClass userClass = new UserClass();
        userClass.setUserName(registrationDto.getUserName());
        userClass.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userClass.setRole("USER");
        userRepository.save(userClass);
    }


    @Override
    public UserClass findByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}