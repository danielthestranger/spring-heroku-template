package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.model.dto.BookSlotDTO;
import com.greenfoxacademy.springherokutemplate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(BookingController.CONTROLLER_ROOT)
public class BookingController {

  public static final String CONTROLLER_ROOT = "/book";

  private BookingService bookingService;

  @Autowired
  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping
  public String bookSlot(@Valid @ModelAttribute BookSlotDTO booking,
                          BindingResult result,
                          Principal principal,
                          RedirectAttributes ra) {
    if (result.hasErrors()) {
      return "theSameTemplate";
    }
    bookingService.bookSlot(principal, booking);
    ra.addAttribute("successMsg", "Appointment booked");
    //TODO add the following to the redirected page:
    //<div th:if="${successMsg != null}" th:text="${successMsg}" class="alert alert-success" role="alert"></div>
    return "redirect:TODO";
  }
}
