package com.greenfoxacademy.springherokutemplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping(value = {"/"})
    public String redirectToTodo() {
        return "redirect:" + HomeController.CONTROLLER_ROOT;
    }
}
