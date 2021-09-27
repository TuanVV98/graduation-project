package com.spring.controller.v1;

import com.spring.dto.model.AccountsDTO;
import com.spring.dto.response.Response;
import com.spring.service.accounts.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountsService accountsService;

    @GetMapping
    public ResponseEntity<List<AccountsDTO>> getAll(){
        return new ResponseEntity<>(accountsService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountsDTO> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(accountsService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Response<AccountsDTO>> register(@Validated @RequestBody AccountsDTO accountsDTO, BindingResult result){
        Response<AccountsDTO> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse((error.getDefaultMessage())));
            return ResponseEntity.badRequest().body(response);
        }
        if(accountsService.checkIdExist(accountsDTO.getId())){
            response.addErrorMsgToResponse("Id này đã được sử dụng");
            return ResponseEntity.badRequest().body(response);
        }
        if(accountsService.checkEmailExist(accountsDTO.getEmail())){
            response.addErrorMsgToResponse("Email này đã được sử dụng");
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.accountsService.register(accountsDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<AccountsDTO>> update(@Validated @RequestBody AccountsDTO accountsDTO, BindingResult result){
        Response<AccountsDTO> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse((error.getDefaultMessage())));
            return ResponseEntity.badRequest().body(response);
        }
        if(accountsService.checkIdExist(accountsDTO.getId())){
            response.addErrorMsgToResponse("Id này đã được sử dụng");
            return ResponseEntity.badRequest().body(response);
        }
        if(accountsService.checkEmailExist(accountsDTO.getEmail())){
            response.addErrorMsgToResponse("Email này đã được sử dụng");
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.accountsService.register(accountsDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Response<AccountsDTO>> deleteById(@PathVariable("id") Long id){
        Response<AccountsDTO> response = new Response<>();
        if(accountsService.checkIdExist(id)){
            response.addErrorMsgToResponse("Id này đã được sử dụng");
            return ResponseEntity.badRequest().body(response);
        }
        accountsService.delete(id);
        return ResponseEntity.ok().build();
    }


}
