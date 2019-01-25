package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;

import java.util.Optional;

public interface AtariCalendarService {
  Optional<AtariCalendar> findById(Long calendarId);
}
