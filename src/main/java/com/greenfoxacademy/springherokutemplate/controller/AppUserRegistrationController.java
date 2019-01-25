package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.email.SendEmail;
import com.greenfoxacademy.springherokutemplate.model.dto.RegistrationForm;
import com.greenfoxacademy.springherokutemplate.service.security.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Controller
@RequestMapping(AppUserRegistrationController.CONTROLLER_ROOT)
public class AppUserRegistrationController {
  public static final String CONTROLLER_ROOT = "/register";

  private AppUserService userService;
  private SendEmail sendEmail;


  @Autowired
  public AppUserRegistrationController(AppUserService userService, SendEmail sendEmail) {
    this.userService = userService;
    this.sendEmail = sendEmail;

  }

  @GetMapping
  public String registerForm(Model model) {
    model.addAttribute("error", "");
    return "register";
  }

  @PostMapping
  public String processRegistration(RegistrationForm regForm, Model model) {
    try {
      userService.createDefaultUser(regForm);
      sendEmail.sendFromGMail(regForm.getEmail(), "Successful registration", "Dear " + regForm.getUsername() +
          ",\nWelcome to Atari Booking Systems, you have successfully registrated.\n Best regards,\n Admin");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      model.addAttribute("error", "User already exists!!!");
      return "register";
    } catch (MessagingException e) {
      model.addAttribute("error", "User already exists!!!");
      System.out.println(e.getCause().getMessage());
      return "register";
    }
    return "redirect:/login";
  }
}
