package com.spring.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.model.CommunesDTO;
import com.spring.dto.response.Response;
import com.spring.service.Communes.CommunesServices;

@RestController
@RequestMapping("api/Communes")
public class CommunesRetsController {
	@Autowired
	CommunesServices communesServices; 
	
	@GetMapping()
	public ResponseEntity<List<CommunesDTO>> getList(){
		return ResponseEntity.ok(communesServices.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Response<CommunesDTO>> getOne(@PathVariable("id") String id) {
		Response<CommunesDTO> response = new Response<>();
		if (!communesServices.existById(id)) {
			response.addErrorMsgToResponse("Xã/Phường này Không tồn tại");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.communesServices.findById(id));
		return ResponseEntity.ok(response);
	}
}
