package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import com.greenfoxacademy.springherokutemplate.model.dto.TimeSlotDTO;

import java.util.List;
import java.util.Optional;

public interface AtariCalendarService {
  Optional<AtariCalendar> findById(Long calendarId);
  List<TimeSlotDTO> findFutureTimeSlotsByCalendarId(Long calendarId);
  List<AtariCalendar> findAll();
  void saveAtariCalendar(AtariCalendar atariCalendar);
  void deleteAtariCalendar(Long id);
  AtariCalendar findCreateOrUpdate(String newName, String serviceTypeName, String atariProviderName, String locationName,  Long id);


  }
