package com.spring.controller.v1.dentist;

import com.spring.dto.model.DentistProfileDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.dentist.DentistService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dentists")
//@Api(value = "Dentist profile Apis")
public class DentistController {
    private final DentistService dentistService;

    @Autowired
    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    // @ApiOperation(value = "Get All Dentist profile", response = List.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
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

//    @ApiOperation(value = "Get All Dentist profile with deleted field is true", response = List.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })

    @GetMapping("/recycle-bin")
    public ResponseEntity<List<DentistProfileDTO>> getAllDentistOnRecycleBin() {
        return ResponseEntity.ok(dentistService.getAllOnRecycleBin());
    }

//    @ApiOperation(value = "Get Dentist profile by id", response = Object.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })

    @GetMapping("/{id}")
    public ResponseEntity<DentistProfileDTO> getDentist(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(dentistService.getById(id));
    }

    // @ApiOperation(value = "Create a dentist profile", response = List.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
    @PostMapping("")
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

    // @ApiOperation(value = "Get All Dentist profile", response = Object.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
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

    // @ApiOperation(value = "Sort delete or restore Dentist profile ")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
    @PatchMapping("/{id}")
    public void sortDeleteDentist(@PathVariable("id") Long id, @RequestParam Boolean deleted) throws NotFoundException {
        dentistService.updateDeleted(id, deleted);
    }

    // @ApiOperation(value = "Hard delete Dentist profile", response = Object.class)
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
    @DeleteMapping("/{id}")
    public void deleteDentist(@PathVariable("id") Long id) throws NotFoundException {
        dentistService.hardDelete(id);
    }
}