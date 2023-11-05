package com.tournament.fifa_tournament;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tournament.fifa_tournament") // pridanie pre funkcnost controlleru v osobitnom package
public class FifaTournamentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FifaTournamentApplication.class, args);
	}

}
