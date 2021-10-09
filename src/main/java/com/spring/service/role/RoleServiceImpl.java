package com.spring.service.role;

import com.spring.dto.model.RolesDTO;
import com.spring.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    private RolesRepository rolesRepository;

    @Autowired
    public RoleServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<RolesDTO> getAll() {
        List<RolesDTO> itemDTO = new ArrayList<>();
        this.rolesRepository.findAll().forEach(t -> itemDTO.add(t.convertEntityToDTO()));
        return itemDTO;
    }
}
