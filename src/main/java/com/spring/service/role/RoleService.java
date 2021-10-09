package com.spring.service.role;

import com.spring.dto.model.RolesDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {

    List<RolesDTO> getAll();

}
