package com.tournament.fifa_tournament.controller;


import com.tournament.fifa_tournament.dataTransferObjects.RegistrationDTO;
import com.tournament.fifa_tournament.models.UserClass;
import com.tournament.fifa_tournament.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//Autorizacia Autentifikacia
@Controller
public class AAController {
    private  UserService userService;

    public AAController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("registrationDTO", registrationDTO);
        return "registration";
    }

    @GetMapping("/login")
    public String loginUser(){
        return "login";
    }

    @PostMapping("/registration/save")
    public String saveUser(@Validated @ModelAttribute("registrationDTO") RegistrationDTO registrationDTO,
                           BindingResult result, Model model) {
        UserClass existUser = userService.findByUserName(registrationDTO.getUserName());
        if(existUser != null && existUser.getUserName() != null && !existUser.getUserName().isEmpty()) {
            return "redirect:/register?fail";
        }

        if(result.hasErrors()){
          model.addAttribute("registrationDTO", registrationDTO);
                  return "registration";
        }

        userService.saveUser(registrationDTO);
        // SOM PRIDAL
        String currentUsername = userService.getCurrentUsername();
        model.addAttribute("username", currentUsername);

        return "redirect:/index?success";

    }

}
