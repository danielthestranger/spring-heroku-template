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

  @Override
  public void deleteServiceType(Long id) {
    serviceTypeRepository.delete(serviceTypeRepository.findById(id).orElse(null));
  }

  @Override
  public ServiceType findById(Long id) {
    return serviceTypeRepository.findById(id).orElse(null);
  }

  @Override
  public ServiceType findCreateOrUpdate(String newName, Long id) {
    if (newName == null || newName.isEmpty()) {
      throw new NullPointerException("Attribute name is null or empty");
    }
    if (id == null) {
      ServiceType savedServiceType = serviceTypeRepository.findByName(newName);
      if (savedServiceType == null) {
        ServiceType serviceType = new ServiceType(newName);
        savedServiceType = serviceTypeRepository.save(serviceType);
        return savedServiceType;
      } else if (savedServiceType.getName().equals(newName)) {
        return savedServiceType;
      }
    }
    ServiceType updateAttribute = serviceTypeRepository.findById(id).orElse(null);
    updateAttribute.setName(newName);
    return serviceTypeRepository.save(updateAttribute);
  }

  public ServiceType findByName(String name) {
    return serviceTypeRepository.findByName(name);
  }
}
