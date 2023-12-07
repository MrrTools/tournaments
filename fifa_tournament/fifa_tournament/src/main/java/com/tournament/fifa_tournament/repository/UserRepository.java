package com.tournament.fifa_tournament.repository;

import com.tournament.fifa_tournament.models.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserClass, Integer> {

    UserClass findByUserName(String userName);

    UserClass findFirstByUserName(String userName);
}
