package com.greenfoxacademy.springherokutemplate.repository;

import com.greenfoxacademy.springherokutemplate.model.AtariCalendar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AtariCalendarRepository extends CrudRepository<AtariCalendar, Long> {

  List<AtariCalendar> findAll();

  AtariCalendar findAllById(Long id);
  AtariCalendar findByName(String name);
}
