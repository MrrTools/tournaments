package com.tournament.fifa_tournament.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {


    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/rules")
    public String rules(){
        return "rules";
    }
    @GetMapping("/galery")
    public String galery(){
        return "galery";
    }
    @GetMapping("/news")
    public String news(){
        return "news";
    }
}
