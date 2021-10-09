package com.spring.service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.dto.model.ServiceDTO;
import com.spring.model.Service;
import com.spring.repository.ServiceRepository;


@org.springframework.stereotype.Service
public class ServicesServiceImpl implements ServicesService{

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public List<ServiceDTO> findAll() {
        List<ServiceDTO> itemsDTO = new ArrayList<>();
        serviceRepository.findAllCustom().stream().forEach(t -> itemsDTO.add(t.convertEntityToDTO()));
        return itemsDTO;
    }

    @Override
    public ServiceDTO findById(Long id) {
        return serviceRepository.findByIdCustom(id).convertEntityToDTO();
    }

    @Override
    public ServiceDTO findByName(String name) {
        return serviceRepository.findByNameCustom(name).convertEntityToDTO();
    }

    @Override
    public ServiceDTO create(ServiceDTO DTO) {
        Service serviceEntity = DTO.convertDTOToEntity();
        serviceEntity.setId(null);
        serviceEntity.setDeleteAt(false);
        return serviceRepository.save(serviceEntity).convertEntityToDTO();
    }

    @Override
    public ServiceDTO update(ServiceDTO DTO) {
        Service serviceEntity = DTO.convertDTOToEntity();
        return serviceRepository.save(serviceEntity).convertEntityToDTO();
    }

    @Override
    public ServiceDTO delete(ServiceDTO DTO) {
        Service serviceEntity = serviceRepository.findByIdCustom(DTO.getId());
        serviceEntity.setDeleteAt(true);
        return serviceRepository.save(serviceEntity).convertEntityToDTO();
    }

    @Override
    public boolean existById(Long id) {
        if (serviceRepository.existByIdCustom(id)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existByName(String name) {
        if (serviceRepository.existByNameCustom(name)) {
            return true;
        }
        return false;
    }
}
