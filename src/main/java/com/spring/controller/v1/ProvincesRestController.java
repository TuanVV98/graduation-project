package com.spring.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.dto.model.ProvincesDTO;
import com.spring.dto.response.Response;
import com.spring.service.provinces.ProvincesServices;

public class ProvincesRestController {
	@Autowired
	ProvincesServices provincesServices;

	@GetMapping()
	public ResponseEntity<List<ProvincesDTO>> getList() {
		return ResponseEntity.ok(provincesServices.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<ProvincesDTO>> getOne(@PathVariable("id") String id) {
		Response<ProvincesDTO> response = new Response<>();
		if (!provincesServices.existById(id)) {
			response.addErrorMsgToResponse("Tỉnh/Thành Phố này Không tồn tại");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.provincesServices.findById(id));
		return ResponseEntity.ok(response);
	}
}
