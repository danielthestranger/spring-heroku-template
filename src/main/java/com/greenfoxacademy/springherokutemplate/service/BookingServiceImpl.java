package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.TimeSlot;
import com.greenfoxacademy.springherokutemplate.model.dto.BookSlotDTO;
import com.greenfoxacademy.springherokutemplate.model.security.AppUser;
import com.greenfoxacademy.springherokutemplate.repository.TimeSlotRepository;
import com.greenfoxacademy.springherokutemplate.service.security.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

  private AppUserService userService;
  private TimeSlotRepository timeSlotRepository;

  @Autowired
  public BookingServiceImpl(AppUserService userService, TimeSlotRepository timeSlotRepository) {
    this.userService = userService;
    this.timeSlotRepository = timeSlotRepository;
  }

  @Override
  public TimeSlot bookSlot(Principal principal, BookSlotDTO booking) {
    Long timeSlotId = booking.getTimeSlotId();

    AppUser user = userService.fromPrincipal(principal);

    TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId).get();
    timeSlot.setBooked(true);
    timeSlot.setBookedBy(user);
    return timeSlotRepository.save(timeSlot);
  }

  @Override
  public Optional<TimeSlot> findById(Long timeSlotID) {
    return timeSlotRepository.findById(timeSlotID);
  }
}
