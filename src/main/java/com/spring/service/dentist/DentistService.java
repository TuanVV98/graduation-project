package com.spring.service.dentist;

import com.spring.dto.model.DentistProfileDTO;
import com.spring.exception.NotFoundException;

import java.util.List;

public interface DentistService {

    List<DentistProfileDTO> getAll();

    List<DentistProfileDTO> getAllOnRecycleBin();

    DentistProfileDTO getById(Long id) throws NotFoundException;

    DentistProfileDTO findByEmail(String email) throws NotFoundException;

    DentistProfileDTO add(DentistProfileDTO dto) throws NotFoundException;

    DentistProfileDTO update(Long id, DentistProfileDTO dto) throws NotFoundException;

    void updateDeleted(Long id, Boolean deleted) throws NotFoundException;

    void hardDelete(Long id) throws NotFoundException;

    List<DentistProfileDTO> getAllByTop(int top);
}
