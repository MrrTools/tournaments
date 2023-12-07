package com.tournament.fifa_tournament.security;

import com.tournament.fifa_tournament.models.UserClass;
import com.tournament.fifa_tournament.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserClass user = userRepository.findByUserName(userName);
        System.out.println("Nove Score: " + user);

        if (user != null) {

            List<GrantedAuthority> authority = Arrays.stream(user.getRole().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    authority);
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    }