package com.spring.service.dentist;

import com.spring.dto.model.CustomerProfileDTO;
import com.spring.dto.model.DentistProfileDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DentistService {
    List<DentistProfileDTO> getAll();
    List<DentistProfileDTO> getAllOnRecycleBin();
    DentistProfileDTO getById(Long id);
    DentistProfileDTO findByEmail(String email);
    DentistProfileDTO add(DentistProfileDTO dto);
    DentistProfileDTO update(Long id, DentistProfileDTO dto);
    void updateDeleted(Long id, Boolean deleted);
    void hardDelete(Long id);
}
