package com.spring.service.dentist;

import com.spring.dto.model.DentistProfileDTO;
import com.spring.exception.NotFoundException;
import com.spring.model.Accounts;
import com.spring.model.DentistProfile;
import com.spring.repository.AccountRepository;
import com.spring.repository.DentistProfileRepository;
import com.spring.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistServiceImpl implements DentistService{

    private final DentistProfileRepository dentistProfileRepository;
    private final MapperUtil mapperList;
    private final AccountRepository accountRepository;
    @Autowired
    public DentistServiceImpl(
            DentistProfileRepository dentistProfileRepository,
            MapperUtil mapperList,
            AccountRepository accountRepository
    ){
        this.dentistProfileRepository = dentistProfileRepository;
        this.mapperList = mapperList;
        this.accountRepository = accountRepository;
    }
    @Override
    public List<DentistProfileDTO> getAll() {
        return mapperList.mapList(dentistProfileRepository.findAllByDeleteAtIsFalse(), DentistProfileDTO.class);
    }

    @Override
    public List<DentistProfileDTO> getAllOnRecycleBin() {
        return mapperList.mapList(dentistProfileRepository.findAllByDeleteAtIsTrue(), DentistProfileDTO.class);
    }

    @Override
    public DentistProfileDTO getById(Long id) {
        return dentistProfileRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Dentist is not found")).convertEntityToDTO();
    }
    @Override
    public DentistProfileDTO findByEmail(String email){
        Accounts accounts = accountRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email is not found"));
        DentistProfile dentist = dentistProfileRepository.findByAccounts(accounts).orElseThrow(() -> new NotFoundException("Dentist is not found"));
        return dentist.convertEntityToDTO();
    }

    @Override
    public DentistProfileDTO add(DentistProfileDTO dto) {
        DentistProfile entity = dto.convertDTOToEntity();
        return dentistProfileRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public DentistProfileDTO update(Long id, DentistProfileDTO dto) {
        DentistProfile dentist = dentistProfileRepository.findById(id).orElseThrow(() -> new NotFoundException("Dentist is not found"));
            dto.setId(dentist.getId());
            DentistProfile entity = dto.convertDTOToEntity();
            return dentistProfileRepository.save(entity).convertEntityToDTO();
    }

    @Override
    public void updateDeleted(Long id, Boolean deleted) {
        DentistProfile entity = dentistProfileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dentist is not found"));
        entity.setDeleteAt(deleted);
        dentistProfileRepository.save(entity);
    }

    @Override
    public void hardDelete(Long id) {
        DentistProfile entity = dentistProfileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dentist is not found"));
        dentistProfileRepository.delete(entity);
    }
}
