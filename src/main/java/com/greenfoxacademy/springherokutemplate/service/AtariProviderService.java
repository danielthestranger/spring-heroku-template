package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.AtariProvider;

import java.util.List;

public interface AtariProviderService {

  List<AtariProvider> findAll();

  void saveAtariProvider(AtariProvider atariProvider);

  void deleteAtariProvider(Long id);

  AtariProvider findById(Long id);

  AtariProvider findCreateOrUpdate(String newName, Long id);

  AtariProvider findByDescription(String description);

}
