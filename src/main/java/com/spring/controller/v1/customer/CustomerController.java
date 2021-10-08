package com.spring.controller.v1.customer;


import com.spring.dto.model.CustomerProfileDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.model.CustomerProfile;
import com.spring.repository.CustomerProfileRepository;
import com.spring.service.customer.CustomerService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
//@Api(value = "Customer profile Apis")
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    CustomerProfileRepository repo;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
//    @ApiOperation(value = "Get all Customer", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
    @PreAuthorize("hasAnyRole('ADMIN, DENTIST')")
    @GetMapping("")
    public ResponseEntity<List<CustomerProfileDTO>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAll());
    }

//    @ApiOperation(value = "Find Customer by id", response = Object.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfileDTO> getCustomer(@PathVariable("id") Long id) throws NotFoundException {

        return ResponseEntity.ok(this.customerService.getById(id));
    }

//    @ApiOperation(value = "Create Customer", response = Object.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
    @PostMapping("")
    public ResponseEntity<Response<CustomerProfileDTO>> createCustomer(@Valid @RequestBody CustomerProfileDTO dto, BindingResult result) throws NotFoundException {
        Response<CustomerProfileDTO> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return  ResponseEntity.badRequest().body(response);
        }
        response.setData(this.customerService.add(dto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @ApiOperation(value = "Update Customer", response = Object.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
    @PutMapping("/{id}")
    public ResponseEntity<Response<CustomerProfileDTO>> updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody CustomerProfileDTO dto, BindingResult result) throws NotFoundException {
        Response<CustomerProfileDTO> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return  ResponseEntity.badRequest().body(response);
        }
        response.setData(this.customerService.update(id, dto));
        return ResponseEntity.ok(response);
    }

//    @ApiOperation(value = "Sort delete or restore Customer by id")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
    @PatchMapping("/{id}")
    public void sortDeleteDentist(@PathVariable("id") Long id, @RequestParam Boolean deleted) throws NotFoundException {
        customerService.updateDeleted(id, deleted);
    }

//    @ApiOperation(value = "Hard delete Customer by id")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success"),
//            @ApiResponse(code = 401, message = "Unconfirmed"),
//            @ApiResponse(code = 403, message = "Unauthorized access"),
//            @ApiResponse(code = 404, message = "Page not found")
//    })
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) throws NotFoundException {
        customerService.deleteById(id);
    }}
