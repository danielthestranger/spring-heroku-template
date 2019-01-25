package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import com.greenfoxacademy.springherokutemplate.repository.AtariCalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtariCalendarServiceImpl implements AtariCalendarService {

  private AtariCalendarRepository calendarRepository;

  @Autowired
  public AtariCalendarServiceImpl(AtariCalendarRepository calendarRepository) {
    this.calendarRepository = calendarRepository;
  }

  @Override
  public Optional<AtariCalendar> findById(Long calendarId) {
    return calendarRepository.findById(calendarId);
  }
}
