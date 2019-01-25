package com.greenfoxacademy.springherokutemplate.repository;

import com.greenfoxacademy.springherokutemplate.model.AtariProvider;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AtariProviderRepository extends CrudRepository<AtariProvider, Long> {

  List<AtariProvider> findAll();
  AtariProvider findByDescription(String description);

}
