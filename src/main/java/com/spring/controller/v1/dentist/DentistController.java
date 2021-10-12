package com.spring.controller.v1.dentist;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.model.DentistProfileDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.dentist.DentistService;

@RestController
@RequestMapping("/api/v1/dentists")
public class DentistController {
	private final DentistService dentistService;

	@Autowired
	public DentistController(DentistService dentistService) {
		this.dentistService = dentistService;
	}

	@GetMapping("")
	public ResponseEntity<Response<List<DentistProfileDTO>>> getAllDentist() {
		Response<List<DentistProfileDTO>> response = new Response<>();
		List<DentistProfileDTO> entity = this.dentistService.getAll();
		response.setData(entity);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/top/{top}")
	public ResponseEntity<Response<List<DentistProfileDTO>>> getAllDentistByTop(@PathVariable("top") int top) {
		Response<List<DentistProfileDTO>> response = new Response<>();
		List<DentistProfileDTO> entity = this.dentistService.getAllByTop(top);
		response.setData(entity);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/recycle-bin")
	public ResponseEntity<List<DentistProfileDTO>> getAllDentistOnRecycleBin() {
		return ResponseEntity.ok(dentistService.getAllOnRecycleBin());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DentistProfileDTO> getDentist(@PathVariable("id") Long id) throws NotFoundException {
		return ResponseEntity.ok(dentistService.getById(id));
	}

	@PreAuthorize("hasAnyRole('ADMIN' or 'RECEPTIONIST')")
	@PostMapping()
	public ResponseEntity<Response<DentistProfileDTO>> createDentist(@Valid @RequestBody DentistProfileDTO dto,
			BindingResult result) throws NotFoundException {
		Response<DentistProfileDTO> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(dentistService.add(dto));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasAnyRole('ADMIN' or 'DENTIST')") // update role chỉ admin
	@PutMapping("/{id}")
	public ResponseEntity<Response<DentistProfileDTO>> updateDentist(@PathVariable("id") Long id,
			@Valid @RequestBody DentistProfileDTO dto, BindingResult result) throws NotFoundException {
		Response<DentistProfileDTO> response = new Response<>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(dentistService.update(id, dto));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasAnyRole('ADMIN')") // xóa mềm
	@PatchMapping("/{id}")
	public void sortDeleteDentist(@PathVariable("id") Long id, @RequestParam Boolean deleted) throws NotFoundException {
		dentistService.updateDeleted(id, deleted);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")// xóa cứng
	@DeleteMapping("/{id}")
	public void deleteDentist(@PathVariable("id") Long id) throws NotFoundException {
		dentistService.hardDelete(id);
	}
}