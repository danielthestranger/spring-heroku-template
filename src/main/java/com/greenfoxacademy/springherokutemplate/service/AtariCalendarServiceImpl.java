package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import com.greenfoxacademy.springherokutemplate.model.TimeSlot;
import com.greenfoxacademy.springherokutemplate.model.dto.TimeSlotDTO;
import com.greenfoxacademy.springherokutemplate.repository.AtariCalendarRepository;
import com.greenfoxacademy.springherokutemplate.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtariCalendarServiceImpl implements AtariCalendarService {

  private AtariCalendarRepository calendarRepository;
  private TimeSlotRepository timeSlotRepository;

  @Autowired
  public AtariCalendarServiceImpl(AtariCalendarRepository calendarRepository, TimeSlotRepository timeSlotRepository) {
    this.calendarRepository = calendarRepository;
    this.timeSlotRepository = timeSlotRepository;
  }

  @Override
  public Optional<AtariCalendar> findById(Long calendarId) {
    return calendarRepository.findById(calendarId);
  }

  @Override
  public List<TimeSlotDTO> findFutureTimeSlotsByCalendarId(Long calendarId) {
    Optional<AtariCalendar> calendar = calendarRepository.findById(calendarId);

    if (!calendar.isPresent()) {
      return null;
    }

    List<TimeSlot> timeSlots =
        timeSlotRepository.findAllByAtariCalendarAndBeginTimeAfterOrderByBeginTimeAsc(calendar.get(), LocalDateTime.now());

    return DTOsFromTimeSlots(timeSlots);
  }

  private TimeSlotDTO DTOFromTimeSlot(TimeSlot timeSlot) {
    TimeSlotDTO dto = new TimeSlotDTO();

    dto.setTimeSlotId(timeSlot.getId());
    dto.setBeginTime(timeSlot.getBeginTime());
    dto.setEndTime(timeSlot.getEndTime());
    dto.setBooked(timeSlot.isBooked());

    if (timeSlot.isBooked()) {
      dto.setComments("unknown");
      dto.setBookedByName("unknown");
    } else {
      dto.setComments("");
      dto.setBookedByName("");
    }

    return dto;
  }

  private List<TimeSlotDTO> DTOsFromTimeSlots(List<TimeSlot> timeSlots) {
    List<TimeSlotDTO> dtos = new ArrayList<>();
    for (TimeSlot timeSlot: timeSlots) {
      dtos.add(DTOFromTimeSlot(timeSlot));
    }

    return dtos;
  }
}
