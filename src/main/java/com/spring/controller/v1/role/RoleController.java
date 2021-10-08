package com.spring.controller.v1.role;


import com.spring.dto.model.RolesDTO;
import com.spring.dto.response.Response;
import com.spring.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Response<List<RolesDTO>>> findAll(){
        Response<List<RolesDTO>> response = new Response<>();

        response.setData(this.roleService.getAll());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
