package com.greenfoxacademy.springherokutemplate.repository;

import com.greenfoxacademy.springherokutemplate.model.TimeSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
}
