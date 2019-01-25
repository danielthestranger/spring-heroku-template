package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.AtariProvider;
import com.greenfoxacademy.springherokutemplate.repository.AtariProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtariProviderServiceImpl implements AtariProviderService {

  private AtariProviderRepository atariProviderRepository;


  @Autowired
  public AtariProviderServiceImpl(AtariProviderRepository atariProviderRepository) {
    this.atariProviderRepository = atariProviderRepository;
  }

  @Override
  public List<AtariProvider> findAll() {
    return atariProviderRepository.findAll();
  }

  @Override
  public void saveAtariProvider(AtariProvider atariProvider) {
    atariProviderRepository.save(atariProvider);
  }

  @Override
  public void deleteAtariProvider(Long id) {
    atariProviderRepository.delete(atariProviderRepository.findById(id).orElse(null));
  }

  @Override
  public AtariProvider findById(Long id) {
    return atariProviderRepository.findById(id).orElse(null);
  }

  @Override
  public AtariProvider findCreateOrUpdate(String newName, Long id) {
    if (newName == null || newName.isEmpty()) {
      throw new NullPointerException("Attribute name is null or empty");
    }
    if (id == null) {
      AtariProvider savedAtariProvider = atariProviderRepository.findByDescription(newName);
      if (savedAtariProvider == null) {
        AtariProvider atariProvider = new AtariProvider(newName);
        savedAtariProvider = atariProviderRepository.save(atariProvider);
        return savedAtariProvider;
      } else if (savedAtariProvider.getDescription().equals(newName)) {
        return savedAtariProvider;
      }
    }
    AtariProvider updateAttribute = atariProviderRepository.findById(id).orElse(null);
    updateAttribute.setDescription(newName);
    return atariProviderRepository.save(updateAttribute);
  }

  @Override
  public AtariProvider findByDescription(String name) {
    return atariProviderRepository.findByDescription(name);
  }
}
