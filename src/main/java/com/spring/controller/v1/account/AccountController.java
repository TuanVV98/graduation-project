package com.spring.controller.v1.account;

import com.spring.dto.model.AccountsDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.service.account.AccountService;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

	private AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	// @GetMapping
//    public ResponseEntity<List<AccountsDTO>> getAll(){
//        return new ResponseEntity<>(accountsService.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AccountsDTO> getById(@PathVariable("id") Long id){
//        return new ResponseEntity<>(accountsService.findById(id), HttpStatus.OK);
//    }

	@PostMapping("/register")
	public ResponseEntity<Response<AccountsDTO>> register(@Validated @RequestBody AccountsDTO accountsDTO,
			BindingResult result) {

		Response<AccountsDTO> response = new Response<>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse((error.getDefaultMessage())));
			return ResponseEntity.badRequest().body(response);
		}
//        if(accountsService.checkIdExist(accountsDTO.getId())){
//            response.addErrorMsgToResponse("Id này đã được sử dụng");
//            return ResponseEntity.badRequest().body(response);
//        }
//        if(accountsService.checkEmailExist(accountsDTO.getEmail())){
//            response.addErrorMsgToResponse("Email này đã được sử dụng");
//            return ResponseEntity.badRequest().body(response);
//        }

		response.setData(this.accountService.register(accountsDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/registerUser")
	public ResponseEntity<Response<AccountsDTO>> registerUser(@Validated @RequestBody AccountsDTO accountsDTO,
			BindingResult result) throws NotFoundException {
		accountsDTO.setRolesId("CUSTOMER");
		Response<AccountsDTO> response = new Response<>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse((error.getDefaultMessage())));
			return ResponseEntity.badRequest().body(response);
		}

		if (accountService.checkIfEmailExistsAndDeletedAt(accountsDTO.getEmail()).isPresent()) {
			throw new NotFoundException("Email này đã tồn tại");
		}

		if (accountService.checkTelephone(accountsDTO.getTelephone()).isPresent()) {
			throw new NotFoundException("Số Điện Thoại này đã tồn tại");
		}

		response.setData(this.accountService.register(accountsDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// api thay tài khoản
	@PutMapping("/{id}")
	public ResponseEntity<Response<AccountsDTO>> update(@Validated @RequestBody AccountsDTO accountsDTO,
			BindingResult result, @PathParam("id") Long id) throws NotFoundException {
		Response<AccountsDTO> response = new Response<>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse((error.getDefaultMessage())));
			return ResponseEntity.badRequest().body(response);
		}
		
		if (!accountService.checkId(id)) {
			throw new NotFoundException("Account này không tồn  tại");
		}
		response.setData(this.accountService.update(accountsDTO));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

//    @PutMapping("/{id}")
//    public ResponseEntity<Response<AccountsDTO>> update(@Validated @RequestBody AccountsDTO accountsDTO, BindingResult result){
//        Response<AccountsDTO> response = new Response<>();
//        if(result.hasErrors()){
//            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse((error.getDefaultMessage())));
//            return ResponseEntity.badRequest().body(response);
//        }
//        if(accountsService.checkIdExist(accountsDTO.getId())){
//            response.addErrorMsgToResponse("Id này đã được sử dụng");
//            return ResponseEntity.badRequest().body(response);
//        }
//        if(accountsService.checkEmailExist(accountsDTO.getEmail())){
//            response.addErrorMsgToResponse("Email này đã được sử dụng");
//            return ResponseEntity.badRequest().body(response);
//        }
//        response.setData(this.accountsService.register(accountsDTO));
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/delete/{id}")
//    public ResponseEntity<Response<AccountsDTO>> deleteById(@PathVariable("id") Long id){
//        Response<AccountsDTO> response = new Response<>();
//        if(accountsService.checkIdExist(id)){
//            response.addErrorMsgToResponse("Id này đã được sử dụng");
//            return ResponseEntity.badRequest().body(response);
//        }
//        accountsService.delete(id);
//        return ResponseEntity.ok().build();
//    }
//

}