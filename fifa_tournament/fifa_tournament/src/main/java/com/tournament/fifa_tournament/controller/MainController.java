package com.tournament.fifa_tournament.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "index.htm";
    }

}