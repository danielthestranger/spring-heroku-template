package com.greenfoxacademy.springherokutemplate.controller;

import com.greenfoxacademy.springherokutemplate.model.dto.BookSlotDTO;
import com.greenfoxacademy.springherokutemplate.model.dto.TimeSlotDTO;
import com.greenfoxacademy.springherokutemplate.service.AtariCalendarService;
import com.greenfoxacademy.springherokutemplate.service.AtariProviderService;
import com.greenfoxacademy.springherokutemplate.service.LocationService;
import com.greenfoxacademy.springherokutemplate.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(BasicSearchController.CONTROLLER_ROOT)
public class BasicSearchController {

  public static final String CONTROLLER_ROOT = "/basicsearch";

  private AtariCalendarService calendarService;

  @Autowired
  public BasicSearchController(AtariCalendarService calendarService) {
    this.calendarService = calendarService;
  }


  @GetMapping(value = "/calendars")
  public String showAllCalendarSummaries(Model model) {
    model.addAttribute("calendarSummaries", calendarService.findAllCalendarSummaries());
    return "basic-search-calendars";
  }

  @GetMapping(value="/calendars/{calendarId}")
  public String showCalendar(@PathVariable Long calendarId,
                             Model model) {
    List<TimeSlotDTO> timeSlotDTOs = calendarService.findFutureTimeSlotsByCalendarId(calendarId);
    model.addAttribute("timeSlotDTOs", timeSlotDTOs);
    model.addAttribute("bookSlotDTO", new BookSlotDTO());
    return "timeslots-by-calendar";
  }
}
