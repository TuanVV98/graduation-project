package com.spring.controller.v1.provinces;

import com.spring.dto.model.ProvincesDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.provinces.ProvincesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/provinces")
public class ProvincesController {

    @Autowired
    private ProvincesServices provincesServices;

    @GetMapping()
    public ResponseEntity<List<ProvincesDTO>> getList() {
        return ResponseEntity.ok(provincesServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ProvincesDTO>> getOne(@PathVariable("id") String id) throws NotFoundException {
        Response<ProvincesDTO> response = new Response<>();
        if (!provincesServices.existById(id)) {
            throw new NotFoundException("Tỉnh/Thành Phố này Không tồn tại");

        }
        response.setData(this.provincesServices.findById(id));
        return ResponseEntity.ok(response);
    }
}
