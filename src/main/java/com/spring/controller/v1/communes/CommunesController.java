package com.spring.controller.v1.communes;

import com.spring.dto.model.CommunesDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.communes.CommunesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/communes")
public class CommunesController {

    @Autowired
    private CommunesServices communesServices;

    @GetMapping()
    public ResponseEntity<List<CommunesDTO>> getList(){
        return ResponseEntity.ok(communesServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<CommunesDTO>> getOne(@PathVariable("id") String id) throws NotFoundException {
        Response<CommunesDTO> response = new Response<>();
        if (!communesServices.existById(id)) {
            throw new NotFoundException("Xã/Phường này Không tồn tại");

        }
        response.setData(this.communesServices.findById(id));
        return ResponseEntity.ok(response);
    }
}
