package com.spring.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.model.DistrictsDTO;
import com.spring.dto.response.Response;
import com.spring.service.districts.DistrictsServices;

@RestController
@RequestMapping("api/districts")
public class DistrictsRestController {
	@Autowired
	DistrictsServices districtsServices;
	
	@GetMapping()
	public ResponseEntity<List<DistrictsDTO>> getList(){
		return ResponseEntity.ok(districtsServices.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Response<DistrictsDTO>> getOne(@PathVariable("id") String id) {
		Response<DistrictsDTO> response = new Response<>();
		if (!districtsServices.existById(id)) {
			response.addErrorMsgToResponse("Huyện/Quận này Không tồn tại");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.districtsServices.findById(id));
		return ResponseEntity.ok(response);
	}
}
