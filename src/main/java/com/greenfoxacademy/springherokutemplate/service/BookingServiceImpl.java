package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.email.SendEmail;
import com.greenfoxacademy.springherokutemplate.model.TimeSlot;
import com.greenfoxacademy.springherokutemplate.model.dto.BookSlotDTO;
import com.greenfoxacademy.springherokutemplate.model.security.AppUser;
import com.greenfoxacademy.springherokutemplate.repository.TimeSlotRepository;
import com.greenfoxacademy.springherokutemplate.service.security.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

  private AppUserService userService;
  private TimeSlotRepository timeSlotRepository;
  private SendEmail sendEmail;

  @Autowired
  public BookingServiceImpl(AppUserService userService, TimeSlotRepository timeSlotRepository, SendEmail sendEmail) {
    this.userService = userService;
    this.timeSlotRepository = timeSlotRepository;
    this.sendEmail = sendEmail;
  }

  @Override
  public TimeSlot bookSlot(Principal principal, BookSlotDTO booking) {
    Long timeSlotId = booking.getTimeSlotId();

    AppUser user = userService.fromPrincipal(principal);

    TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId).get();
    timeSlot.setBooked(true);
    timeSlot.setBookedBy(user);

    TimeSlot bookedSlot = timeSlotRepository.save(timeSlot);

    try {
      sendEmail.sendFromGMail(user.getEmail(), "Booked appointment", "Dear " + user.getUsername() +
          ", \n You have booked an appointment at location: " + bookedSlot.getAtariCalendar().getLocation().getName() + " at " + bookedSlot.getBeginTime() + ". " +
          "\n Best regards,\n Admin");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return bookedSlot;
  }

  @Override
  public Optional<TimeSlot> findById(Long timeSlotID) {
    return timeSlotRepository.findById(timeSlotID);
  }
}
