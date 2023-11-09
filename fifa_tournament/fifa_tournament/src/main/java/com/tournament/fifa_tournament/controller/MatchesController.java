package com.tournament.fifa_tournament.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatchesController {


    @GetMapping("/zapasy")
    public String zapasy(){
        return "zapasy";
    }
}
