package com.greenfoxacademy.springherokutemplate.repository;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import com.greenfoxacademy.springherokutemplate.model.TimeSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
  List<TimeSlot> findAllByAtariCalendarAndBeginTimeAfterOrderByBeginTimeAsc(AtariCalendar calendar, LocalDateTime beginTime);
}
