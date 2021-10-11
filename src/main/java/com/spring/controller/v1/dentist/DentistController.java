package com.spring.controller.v1.dentist;

import com.spring.dto.model.DentistProfileDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.dentist.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

//
    @GetMapping("/recycle-bin")
    public ResponseEntity<List<DentistProfileDTO>> getAllDentistOnRecycleBin() {
        return ResponseEntity.ok(dentistService.getAllOnRecycleBin());
    }

//

    @GetMapping("/{id}")
    public ResponseEntity<DentistProfileDTO> getDentist(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(dentistService.getById(id));
    }


    @PostMapping()
    public ResponseEntity<Response<DentistProfileDTO>> createDentist(
            @Valid @RequestBody DentistProfileDTO dto,
            BindingResult result) throws NotFoundException {
        Response<DentistProfileDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(dentistService.add(dto));
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response<DentistProfileDTO>> updateDentist(
            @PathVariable("id") Long id,
            @Valid @RequestBody DentistProfileDTO dto, BindingResult result) throws NotFoundException {
        Response<DentistProfileDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(dentistService.update(id, dto));
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{id}")
    public void sortDeleteDentist(@PathVariable("id") Long id, @RequestParam Boolean deleted) throws NotFoundException {
        dentistService.updateDeleted(id, deleted);
    }


    @DeleteMapping("/{id}")
    public void deleteDentist(@PathVariable("id") Long id) throws NotFoundException {
        dentistService.hardDelete(id);
    }
}