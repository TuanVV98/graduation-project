package com.spring.service.dentist;

import com.spring.dto.model.DentistProfileDTO;
import com.spring.exception.NotFoundException;
import com.spring.model.Accounts;
import com.spring.model.DentistProfile;
import com.spring.repository.AccountRepository;
import com.spring.repository.DentistProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistServiceImpl implements DentistService{

    private final DentistProfileRepository dentistProfileRepository;
    private final AccountRepository accountRepository;
    @Autowired
    public DentistServiceImpl(
            DentistProfileRepository dentistProfileRepository,
            AccountRepository accountRepository){
        this.dentistProfileRepository = dentistProfileRepository;
        this.accountRepository = accountRepository;
    }
    @Override
    public List<DentistProfileDTO> getAll() {

        List<DentistProfileDTO> itemDTO = new ArrayList<>();
        this.dentistProfileRepository.findAllByDeleteAtIsFalse().forEach(t ->itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    @Override
    public List<DentistProfileDTO> getAllByTop(int top) {
        List<DentistProfileDTO> itemDTO = new ArrayList<>();
        this.dentistProfileRepository.findAllByTop(top).forEach(t ->itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    @Override
    public List<DentistProfileDTO> getAllOnRecycleBin() {
        List<DentistProfileDTO> itemDTO = new ArrayList<>();
        this.dentistProfileRepository.findAllByDeleteAtIsTrue().forEach(t ->itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }

    @Override
    public DentistProfileDTO getById(Long id) throws NotFoundException {
        return dentistProfileRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Dentist is not found")).convertEntityToDTO();
    }
    @Override
    public DentistProfileDTO findByEmail(String email)throws NotFoundException{
        Accounts accounts = accountRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email is not found"));
        DentistProfile dentist = dentistProfileRepository.findByAccounts(accounts).orElseThrow(() -> new NotFoundException("Dentist is not found"));
        return dentist.convertEntityToDTO();
    }

    @Override
    public DentistProfileDTO add(DentistProfileDTO dto) throws NotFoundException {
        DentistProfile entity = dto.convertDTOToEntity();
        Accounts accounts = accountRepository.findById(dto.getId()).orElseThrow(() -> new NotFoundException("Accounts is not found"));
        entity.setAccounts(accounts);
        return dentistProfileRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public DentistProfileDTO update(Long id, DentistProfileDTO dto) throws NotFoundException {
        DentistProfile dentist = dentistProfileRepository.findById(id).orElseThrow(() -> new NotFoundException("Dentist is not found"));
        dto.setId(dentist.getId());
        DentistProfile entity = dto.convertDTOToEntity();
        return dentistProfileRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public void updateDeleted(Long id, Boolean deleted) throws NotFoundException {
        DentistProfile entity = dentistProfileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dentist is not found"));
        entity.setDeleteAt(deleted);
        dentistProfileRepository.save(entity);
    }

    @Override
    public void hardDelete(Long id) throws NotFoundException{
        DentistProfile entity = dentistProfileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dentist is not found"));
        dentistProfileRepository.delete(entity);
    }
}
