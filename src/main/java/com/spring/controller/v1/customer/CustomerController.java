package com.spring.controller.v1.customer;


import com.spring.dto.model.CustomerProfileDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
//
    @GetMapping("")
    public ResponseEntity<List<CustomerProfileDTO>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAll());
    }

//
    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfileDTO> getCustomer(@PathVariable("id") Long id) throws NotFoundException {

        return ResponseEntity.ok(this.customerService.getById(id));
    }

//
    @PostMapping()
    public ResponseEntity<Response<CustomerProfileDTO>> createCustomer(@Valid @RequestBody CustomerProfileDTO dto, BindingResult result) throws NotFoundException {
        Response<CustomerProfileDTO> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return  ResponseEntity.badRequest().body(response);
        }
        response.setData(this.customerService.add(dto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//
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

//
    @PatchMapping("/{id}")
    public void sortDeleteDentist(@PathVariable("id") Long id, @RequestParam Boolean deleted) throws NotFoundException {
        customerService.updateDeleted(id, deleted);
    }

//
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) throws NotFoundException {
        customerService.deleteById(id);
    }}
