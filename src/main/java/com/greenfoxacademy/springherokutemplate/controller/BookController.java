package com.greenfoxacademy.springherokutemplate.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(BookController.CONTROLLER_ROOT)
public class BookController {

  public static final String CONTROLLER_ROOT = "/book";

  @GetMapping
  public String getPrincipal(Principal principal) {
    String username = principal.getName();
    UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)principal;
    userToken.getAuthorities();
    userToken.getName();
    return "home";
  }
}
