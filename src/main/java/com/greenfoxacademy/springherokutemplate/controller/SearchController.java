package com.greenfoxacademy.springherokutemplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping("/")
    public String searchPage(Model model) {
        // model: available locations, providers (of locations), service types (of locations), timeslots (of locations)
        return "search";
    }

    @PostMapping("/")
    public String searchedValues() {
        // @RequestParam (locationId, providerId, service typeId, timeslot)
        // service search
        // return "redirect:/"
        return null;
    }
}