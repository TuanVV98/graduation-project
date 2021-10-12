package com.spring.controller.v1.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.model.ServiceDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.services.ServicesService;

@RestController
@RequestMapping("api/v1/services")
public class ServiceController {

	private final ServicesService serviceService;

	@Autowired
	public ServiceController(ServicesService serviceService) {
		this.serviceService = serviceService;
	}

	@GetMapping("")
	public ResponseEntity<List<ServiceDTO>> getAll() {
		return ResponseEntity.ok(serviceService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<ServiceDTO>> getOne(@PathVariable("id") Long id) throws NotFoundException {
		Response<ServiceDTO> response = new Response<>();
		if (!serviceService.existById(id)) {
			throw new NotFoundException("Dịch vụ này Không tồn tại");
		}
		response.setData(this.serviceService.findById(id));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasAnyRole('ADMIN' or 'RECEPTIONIST')")
	@PostMapping()
	public ResponseEntity<Response<ServiceDTO>> postServices(@Valid @RequestBody() ServiceDTO servicesDTO,
			BindingResult result) throws NotFoundException {
		Response<ServiceDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		if (serviceService.existByName(servicesDTO.getName())) {
			throw new NotFoundException("Dịch vụ này Không tồn tại");
		}

		response.setData(serviceService.create(servicesDTO));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ADMIN' or 'RECEPTIONIST')")
	@PutMapping("/{id}")
	public ResponseEntity<Response<ServiceDTO>> putServices(@Valid @RequestBody() ServiceDTO servicesDTO,
			BindingResult result, @PathVariable("id") Long id) throws NotFoundException {
		Response<ServiceDTO> response = new Response<>();
		// chưa check quền cập nhật role
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		if (!serviceService.existById(id)) {
			throw new NotFoundException("Dịch vụ này Không tồn tại");
		}
		response.setData(serviceService.update(servicesDTO));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<ServiceDTO>> deleteServices(@PathVariable("id") Long id) throws NotFoundException {
		Response<ServiceDTO> response = new Response<>();
		if (!serviceService.existById(id)) {
			throw new NotFoundException("Dịch vụ này Không tồn tại");
		} else {
			ServiceDTO svDTO = serviceService.findById(id);
			response.setData(serviceService.delete(svDTO));
		}
		return ResponseEntity.ok(response);
	}
}
