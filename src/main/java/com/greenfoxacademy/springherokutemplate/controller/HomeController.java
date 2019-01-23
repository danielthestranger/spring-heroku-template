package com.greenfoxacademy.springherokutemplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(HomeController.CONTROLLER_ROOT)
public class HomeController {

    public static final String CONTROLLER_ROOT = "/home";
//    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String list(Model model) {
        return "home";
    }
}
