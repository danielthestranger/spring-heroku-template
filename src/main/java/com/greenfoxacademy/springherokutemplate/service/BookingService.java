package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.TimeSlot;
import com.greenfoxacademy.springherokutemplate.model.dto.BookSlotDTO;

import java.security.Principal;
import java.util.Optional;

public interface BookingService {
  TimeSlot bookSlot(Principal principal, BookSlotDTO booking);
  Optional<TimeSlot> findById(Long timeSlotID);
}
