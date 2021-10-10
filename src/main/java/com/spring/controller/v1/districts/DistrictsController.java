package com.spring.controller.v1.districts;

import com.spring.dto.model.DistrictsDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.districts.DistrictsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/districts")
public class DistrictsController {

    @Autowired
    private DistrictsServices districtsServices;

    @GetMapping()
    public ResponseEntity<List<DistrictsDTO>> getList(){
        return ResponseEntity.ok(districtsServices.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<DistrictsDTO>> getOne(@PathVariable("id") String id) throws NotFoundException {
        Response<DistrictsDTO> response = new Response<>();
        if (!districtsServices.existById(id)) {
            throw new NotFoundException("Huyện/Quận này Không tồn tại");

        }
        response.setData(this.districtsServices.findById(id));
        return ResponseEntity.ok(response);
    }
}
