package com.greenfoxacademy.springherokutemplate.service;

import com.greenfoxacademy.springherokutemplate.model.ServiceType;
import com.greenfoxacademy.springherokutemplate.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

  private ServiceTypeRepository serviceTypeRepository;

  @Autowired
  public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository) {
    this.serviceTypeRepository = serviceTypeRepository;
  }

  @Override
  public List<ServiceType> findAll() {
    return serviceTypeRepository.findAll();
  }

  public void saveServiceType(ServiceType serviceType) {
    serviceTypeRepository.save(serviceType);
  }
}
