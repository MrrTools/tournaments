package com.tournament.fifa_tournament.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MatchesController {


    @GetMapping("/zapasy")
    public String zapasy(){
        return "zapasy";
    }


    @RequestMapping(value="/zapasy")
    public String testMethod() {
        System.out.println("Success");
        return "zapasy";
    }

}