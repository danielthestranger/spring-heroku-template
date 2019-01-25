package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.model.dto.BookSlotDTO;
import com.greenfoxacademy.springherokutemplate.model.dto.TimeSlotDTO;
import com.greenfoxacademy.springherokutemplate.service.AtariCalendarService;
import com.greenfoxacademy.springherokutemplate.service.BookingService;
import com.greenfoxacademy.springherokutemplate.util.ValidationUtil;
import com.greenfoxacademy.springherokutemplate.validator.BookSlotDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(BookingController.CONTROLLER_ROOT)
public class BookingController {

  public static final String CONTROLLER_ROOT = "/book";

  private BookingService bookingService;
  private AtariCalendarService atariCalendarService;

  @Autowired
  public BookingController(BookingService bookingService, AtariCalendarService atariCalendarService) {
    this.bookingService = bookingService;
    this.atariCalendarService = atariCalendarService;
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.setValidator(new BookSlotDTOValidator(bookingService));
  }


  @GetMapping(value="/{calendarId}")
  public String showCalendar(@PathVariable Long calendarId,
                             Model model) {
    List<TimeSlotDTO> timeSlotDTOs = atariCalendarService.findFutureTimeSlotsByCalendarId(calendarId);
    model.addAttribute("timeSlotDTOs", timeSlotDTOs);
    model.addAttribute("bookSlotDTO", new BookSlotDTO());
    return "timeslots-by-calendar";
  }

  @PostMapping
  public String bookSlot(@Valid @ModelAttribute BookSlotDTO booking,
                          BindingResult result,
                          Principal principal,
                          RedirectAttributes ra,
                          Model model) {
    if (result.hasErrors()) {
      model.addAttribute("errors", ValidationUtil.extractErrorStrings(result));
      return "timeslots-by-calendar";
    }
    bookingService.bookSlot(principal, booking);
    ra.addFlashAttribute("successMsg", "Appointment booked");
    return "redirect:/search";
  }
}
