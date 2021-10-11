package com.spring.controller.v1.account;

import com.spring.dto.model.AccountsDTO;
import com.spring.dto.response.Response;
import com.spring.exception.NotFoundException;
import com.spring.exception.NotParsableContentException;
import com.spring.model.VerificationToken;
import com.spring.service.account.AccountService;
import com.spring.service.verificationToken.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private AccountService accountService;
    private VerificationTokenService verificationTokenService;

    @Autowired
    public AccountController(AccountService accountService,VerificationTokenService verificationTokenService) {
        this.accountService = accountService;
        this.verificationTokenService = verificationTokenService;
    }

    //    @GetMapping
//    public ResponseEntity<List<AccountsDTO>> getAll(){
//        return new ResponseEntity<>(accountsService.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AccountsDTO> getById(@PathVariable("id") Long id){
//        return new ResponseEntity<>(accountsService.findById(id), HttpStatus.OK);
//    }
//
    @PostMapping("/register")
    public ResponseEntity<Response<AccountsDTO>> register(@Validated @RequestBody AccountsDTO accountsDTO, BindingResult result) throws NotFoundException {
        
        Response<AccountsDTO> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse((error.getDefaultMessage())));
            return ResponseEntity.badRequest().body(response);
        }

        if(this.accountService.checkIfEmailExistsAndDeletedAt(accountsDTO.getEmail()).isPresent()){
            throw new NotFoundException("Email này đã được sử dụng");
        }

        if (this.accountService.checkTelephone(accountsDTO.getTelephone()).isPresent()) {
            throw new NotFoundException("Số Điện Thoại này đã tồn tại");
        }

        response.setData(this.accountService.register(accountsDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/verify")
    public ResponseEntity<?> verifyAccount(@RequestParam(required = false) String token) throws NotParsableContentException, NotFoundException {
        System.out.println("**** Calling verify account****");

        Response<?> response = new Response<>();

        Optional<VerificationToken> checkConfirmToken = this.verificationTokenService.checkComfirmToken(token);

        if(checkConfirmToken.isEmpty()) {
           throw new NotFoundException("Account has been activated ! Thank you !");
        }

        boolean verifyAccount = this.accountService.verifyAccount(checkConfirmToken);

        if(!verifyAccount) {
            throw new NotParsableContentException("Token is not valid or token has expired !");
        };

        return new ResponseEntity<>("Confirm !!",HttpStatus.OK);
    }

    @GetMapping(value = "/verify-change-password")
    public ResponseEntity<?> verifyChangePassword(@RequestParam(required = false) String token) throws NotParsableContentException, NotFoundException {

        Response<?> response = new Response<>();

        Optional<VerificationToken> checkConfirmToken = this.verificationTokenService.checkComfirmToken(token);

        if(checkConfirmToken.isEmpty()) {
            throw new NotFoundException("Token already used ! Thank you !");
        }

        boolean verifyAccount = this.accountService.verifyChangePassword(checkConfirmToken);

        if(!verifyAccount) {
            throw new NotParsableContentException("Token has expired !");
        };

        return new ResponseEntity<>("Confirm !!",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<AccountsDTO>> update(@Validated @RequestBody AccountsDTO accountsDTO, BindingResult result){
        Response<AccountsDTO> response = new Response<>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.addErrorMsgToResponse((error.getDefaultMessage())));
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(this.accountService.update(accountsDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        this.accountService.delete(id);
        return ResponseEntity.ok().build();
    }

}