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
  private ServiceTypeService serviceTypeService;
  private LocationService locationService;
  private AtariProviderService atariProviderService;

  @Autowired
  public AtariCalendarServiceImpl(AtariCalendarRepository calendarRepository, TimeSlotRepository timeSlotRepository, ServiceTypeService serviceTypeService, LocationService locationService, AtariProviderService atariProviderService) {
    this.calendarRepository = calendarRepository;
    this.timeSlotRepository = timeSlotRepository;
    this.serviceTypeService = serviceTypeService;
    this.locationService = locationService;
    this.atariProviderService = atariProviderService;
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
    dto.setComments("unknown");
    dto.setBookedByName("unknown");

    return dto;
  }

  private List<TimeSlotDTO> DTOsFromTimeSlots(List<TimeSlot> timeSlots) {
    List<TimeSlotDTO> dtos = new ArrayList<>();
    for (TimeSlot timeSlot: timeSlots) {
      dtos.add(DTOFromTimeSlot(timeSlot));
    }

    return dtos;
  }

  @Override
  public List<AtariCalendar> findAll() {
    return calendarRepository.findAll();
  }

  @Override
  public void saveAtariCalendar(AtariCalendar atariCalendar) {
    calendarRepository.save(atariCalendar);
  }

  @Override
  public void deleteAtariCalendar(Long id) {
    calendarRepository.delete(calendarRepository.findById(id).orElse(null));
  }

  @Override
  public AtariCalendar findCreateOrUpdate(String newName, String serviceTypeName, String atariProviderName, String locationName,  Long id) {
    if (newName == null || newName.isEmpty()) {
      throw new NullPointerException("Attribute name is null or empty");
    }
    if (id == null) {
      AtariCalendar savedAtariCalendar = calendarRepository.findByName(newName);
      if (savedAtariCalendar == null) {
        AtariCalendar serviceType = new AtariCalendar(newName, serviceTypeService.findByName(serviceTypeName), atariProviderService.findByDescription(atariProviderName), null, null, locationService.findByName(locationName) );
        savedAtariCalendar = calendarRepository.save(serviceType);
        return savedAtariCalendar;
      } else if (savedAtariCalendar.getName().equals(newName)) {
        return savedAtariCalendar;
      }
    }
    AtariCalendar updateAttribute = calendarRepository.findById(id).orElse(null);
    updateAttribute.setName(newName);
    updateAttribute.setServiceType(serviceTypeService.findByName(serviceTypeName));
    updateAttribute.setAtariProvider(atariProviderService.findByDescription(atariProviderName));
    updateAttribute.setLocation(locationService.findByName(locationName));
    return calendarRepository.save(updateAttribute);
  }


}
