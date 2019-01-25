package com.greenfoxacademy.springherokutemplate.validator;

import com.greenfoxacademy.springherokutemplate.model.TimeSlot;
import com.greenfoxacademy.springherokutemplate.model.dto.BookSlotDTO;
import com.greenfoxacademy.springherokutemplate.service.AtariCalendarService;
import com.greenfoxacademy.springherokutemplate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public class BookSlotDTOValidator implements Validator {

  private BookingService bookingService;

  public BookSlotDTOValidator(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return BookSlotDTO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    BookSlotDTO booking = (BookSlotDTO) target;

    Long timeSlotId = booking.getTimeSlotId();

    Optional<TimeSlot> timeSlot = bookingService.findById(timeSlotId);
    if (!timeSlot.isPresent()) {
      errors.rejectValue("timeSlotId", "INVALID_SLOT", "Invalid timeslot");
    } else if (timeSlot.get().isBooked()) {
      errors.rejectValue("timeSlotId", "SLOT_BOOKED", "This timeslot is already booked");
    }

  }
}
