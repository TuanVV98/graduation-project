package com.spring.controller.v1;

import com.spring.dto.model.CustomerProfileDTO;
import com.spring.dto.response.Response;
import com.spring.service.customer.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "Customer profile Apis")
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Get all Customer", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unconfirmed"),
            @ApiResponse(code = 403, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Page not found")
    })
    @GetMapping("")
    public ResponseEntity<List<CustomerProfileDTO>> getAllCustomer(){

        return ResponseEntity.ok(customerService.getAll());
    }

    @ApiOperation(value = "Find Customer by id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unconfirmed"),
            @ApiResponse(code = 403, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Page not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerProfileDTO> getCustomer(@PathVariable("id") Long id){

        return ResponseEntity.ok(customerService.getById(id));
    }

    @ApiOperation(value = "Create Customer", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unconfirmed"),
            @ApiResponse(code = 403, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Page not found")
    })
    @PostMapping("")
    public ResponseEntity<Response<CustomerProfileDTO>> createCustomer(@Valid CustomerProfileDTO dto, BindingResult result){
        Response response = new Response();
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return  ResponseEntity.badRequest().body(response);
        }
        Date currentDate = new Date();
        dto.setCreateAt(currentDate);
        response.setData(this.customerService.add(dto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Customer", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unconfirmed"),
            @ApiResponse(code = 403, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Page not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Response<CustomerProfileDTO>> updateCustomer(@PathVariable("id") Long id, @Valid CustomerProfileDTO dto, BindingResult result){
        Response response = new Response();
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
            return  ResponseEntity.badRequest().body(response);
        }
        Date currentDate = new Date();
        dto.setUpdateAt(currentDate);
        response.setData(this.customerService.update(id, dto));
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Delete Customer by id", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unconfirmed"),
            @ApiResponse(code = 403, message = "Unauthorized access"),
            @ApiResponse(code = 404, message = "Page not found")
    })
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteById(id);
    }
}